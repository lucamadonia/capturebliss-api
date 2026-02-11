ALTER TABLE capturebliss_app.user
    ADD COLUMN auth_id            VARCHAR(255),
    ADD COLUMN domain_blacklisted BOOL;

ALTER TABLE capturebliss_app.org
    ADD COLUMN domain varchar(50) NOT NULL;
CREATE INDEX IDX_domain ON capturebliss_app.org (domain);
