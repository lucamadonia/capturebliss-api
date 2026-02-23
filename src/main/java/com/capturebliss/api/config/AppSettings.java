package com.capturebliss.api.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.capturebliss.api.entity.Settings;
import com.capturebliss.api.repo.AppSettingsRepo;
import com.capturebliss.api.transport.SchemaVersion;
import io.sentry.Sentry;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "com.capturebliss.api.self")
@Getter
@Slf4j
public class AppSettings {
  @Getter(AccessLevel.NONE)
  private final AppSettingsRepo settingsRepo;
  private final ObjectMapper mapper = new ObjectMapper();
  private SchemaVersion currentSchemaVersion;
  private String onboardingTourIds;
  private boolean isMigrationFlatSet;
  private boolean isDataEntryFlagSet;
  private int maxSSLCertPerClusterLimit;
  private List<CustomDomainProxyCluster> customDomainProxyClusters = new ArrayList<>();
  private Map<String, Object> featurePlanMatrix;
  private Object globalOpts;
  @Setter
  private String publicEndpoint;

  @Autowired
  public AppSettings(AppSettingsRepo settingsRepo) {
    this.settingsRepo = settingsRepo;
    load();
  }

  @SuppressWarnings("unchecked")
  private Map<String, Object> getDefaultFeaturePlanMatrix() {
    try {
      String json = """
        {
          "modules":{"plans":[{"test":"switch","value":"true","plan":"*"}],"isInBeta":false,"requireAccess":false},
          "no_of_demos":{"plans":[{"test":"count","value":"<=1000","plan":"*"}],"isInBeta":false,"requireAccess":false},
          "aggregate_analytics":{"plans":[{"test":"switch","value":"true","plan":"*"}],"isInBeta":false,"requireAccess":false},
          "custom_domain":{"plans":[{"test":"switch","value":"true","plan":"*"}],"isInBeta":false,"requireAccess":false},
          "demo_hub":{"plans":[{"test":"switch","value":"true","plan":"*"}],"isInBeta":false,"requireAccess":false},
          "no_watermark":{"plans":[{"test":"switch","value":"true","plan":"*"}],"isInBeta":false,"requireAccess":false},
          "custom_demo_loader":{"plans":[{"test":"switch","value":"true","plan":"*"}],"isInBeta":false,"requireAccess":false},
          "multi_annontation":{"plans":[{"test":"switch","value":"true","plan":"*"}],"isInBeta":false,"requireAccess":false},
          "custom_lead_form":{"plans":[{"test":"switch","value":"true","plan":"*"}],"isInBeta":false,"requireAccess":false},
          "no_of_creator":{"plans":[{"test":"count","value":"<=100","plan":"*"}],"isInBeta":false,"requireAccess":false},
          "dataset":{"plans":[{"test":"switch","value":"true","plan":"*"}],"isInBeta":false,"requireAccess":false},
          "integration":{"plans":[{"test":"text","value":["*"],"plan":"*"}],"isInBeta":false,"requireAccess":false}
        }
        """;
      return mapper.readValue(json, new TypeReference<>() {});
    } catch (Exception e) {
      log.error("Failed to parse default feature plan matrix", e);
      return new HashMap<>();
    }
  }

  private List<CustomDomainProxyCluster> parseCustomDomainClusterIdentifiers(String str) {
    String[] clusters = StringUtils.split(str, ';');
    ArrayList<CustomDomainProxyCluster> list = new ArrayList<>();

    for (String cluster : clusters) {
      String[] clusterIds = cluster.split("#");
      list.add(new CustomDomainProxyCluster(clusterIds[0], clusterIds[1]));
    }

    return list;
  }

  public void load() {
    Iterable<Settings> settings = settingsRepo.findAll();
    HashMap<String, String> hm = new HashMap<>();
    for (Settings setting : settings) {
      hm.put(setting.getK(), setting.getV());
    }

    currentSchemaVersion = SchemaVersion.of(hm.get("CURRENT_SCHEMA_VERSION"));
    onboardingTourIds = hm.getOrDefault("ONBOARDING_TOUR_IDS", "");
    globalOpts = hm.getOrDefault("DEFAULT_GLOBAL_OPTS", null);

    try {
      String featurePlanJson = hm.getOrDefault("FEATURE_PLAN_MATRIX", null);
      if (featurePlanJson != null) {
        TypeReference<Map<String, Object>> typeRef = new TypeReference<>() {
        };
        featurePlanMatrix = mapper.readValue(featurePlanJson, typeRef);
      }
    } catch (Exception e) {
      log.error("Something went wrong with getting #featurePerPlan {}", e.getMessage());
      Sentry.captureException(e);
    }

    if (featurePlanMatrix == null) {
      log.info("FEATURE_PLAN_MATRIX not found in settings, using default (all features enabled)");
      featurePlanMatrix = getDefaultFeaturePlanMatrix();
    }

    // Turn this on when migration scripts are run and needs to access api for migration
    String migrationFlagRaw = hm.getOrDefault("MIGRATION_FLAG", "0");
    isMigrationFlatSet = StringUtils.equals(migrationFlagRaw, "1");

    // Turn this on when manual data entry of a table is required
    String dataEntryFlagRaw = hm.getOrDefault("DATA_ENTRY_FLAG", "0");
    isDataEntryFlagSet = StringUtils.equals(dataEntryFlagRaw, "1");

    // In the format
    // cluster-name1#id;cluster-name2#id
    String clusters = hm.getOrDefault("CUSTOM_DOMAIN_PROXY_CLUSTERS", "");
    customDomainProxyClusters = parseCustomDomainClusterIdentifiers(clusters);

    String maxSsl = hm.getOrDefault("MAX_SSL_CERT_PER_CLUSTER", "40");
    maxSSLCertPerClusterLimit = Integer.parseInt(maxSsl);

    log.info("Settings loaded currentSchemaVersion=[{}] customDomainProxyClusters=[{}] onboardingTourIds=[{}] migrationFlag=[{}] dataEntryFlag=[{}] featurePlanMatrix=[{}], globalOpts=[{}]",
      currentSchemaVersion,
      customDomainProxyClusters,
      onboardingTourIds,
      isMigrationFlatSet,
      isDataEntryFlagSet,
      featurePlanMatrix,
      globalOpts
    );
  }
}

