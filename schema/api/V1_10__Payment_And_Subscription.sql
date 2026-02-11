CREATE TABLE capturebliss_app.subscriptions
(
    id                 BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    created_at         TIMESTAMP       NOT NULL,
    updated_at         TIMESTAMP       NOT NULL,
    org_id             BIGINT UNSIGNED NOT NULL,
    payment_plan_id    VARCHAR(100)    NOT NULL,
    payment_plan       VARCHAR(100)    NOT NULL,
    payment_interval   VARCHAR(100)    NOT NULL,
    cb_customer_id     VARCHAR(255)    NOT NULL,
    cb_subscription_id VARCHAR(255)    NOT NULL,
    status             VARCHAR(20)     NOT NULL,
    trial_ends_on      TIMESTAMP,
    trial_started_on   TIMESTAMP,
    info               JSON,

    CONSTRAINT FK_org_id FOREIGN KEY (org_id) REFERENCES org (id)
);
CREATE INDEX IDX_subscriptions_org_id ON capturebliss_app.subscriptions (org_id);
CREATE INDEX IDX_subscriptions_id ON capturebliss_app.subscriptions (cb_subscription_id);

ALTER TABLE capturebliss_app.user
    ADD active BOOLEAN DEFAULT TRUE;

DROP INDEX IDX_org ON capturebliss_app.user;
CREATE INDEX IDX_org_active ON capturebliss_app.user (belongs_to_org, active);
