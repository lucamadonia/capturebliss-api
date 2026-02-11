ALTER TABLE capturebliss_app.tour
    ADD deleted INTEGER DEFAULT 0 NOT NULL;

CREATE INDEX IDX_org_deleted ON capturebliss_app.tour (belongs_to_org, deleted);
CREATE INDEX IDX_rid_deleted ON capturebliss_app.tour (rid, deleted);

DROP INDEX IDX_org ON capturebliss_app.tour;
DROP INDEX IDX_rid ON capturebliss_app.tour;