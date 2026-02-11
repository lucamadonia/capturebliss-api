ALTER TABLE capturebliss_app.tour
    ADD entity_type INTEGER DEFAULT 0 NOT NULL,
    ADD info JSON NULL;

CREATE INDEX IDX_org_deleted_entity ON capturebliss_app.tour (belongs_to_org, entity_type, deleted);

DROP INDEX IDX_org_deleted ON capturebliss_app.tour;