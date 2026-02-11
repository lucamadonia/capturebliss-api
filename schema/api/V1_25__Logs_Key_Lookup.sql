ALTER TABLE capturebliss_app.logs
    ADD for_object_key varchar(255);

CREATE INDEX IDX_log_key ON capturebliss_app.logs (org_id, log_type, for_object_type, for_object_key);

ALTER TABLE capturebliss_app.subscriptions
    ADD managed_by varchar(255) default 'CHARGEBEE';
