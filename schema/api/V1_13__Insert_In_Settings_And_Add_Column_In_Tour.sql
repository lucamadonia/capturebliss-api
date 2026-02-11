INSERT INTO capturebliss_app.settings (k, v)
VALUES ('ONBOARDING_TOUR_IDS', '150,151');

ALTER TABLE capturebliss_app.tour
    ADD onboarding BOOLEAN DEFAULT FALSE;
