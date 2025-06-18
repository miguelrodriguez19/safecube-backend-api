-- Users ------------------------------------------------------------
CREATE TABLE users (
    id                   UUID PRIMARY KEY,
    email                VARCHAR(320) NOT NULL,
    pin_salt             BYTEA        NOT NULL,
    pin_hash             BYTEA        NOT NULL,
    email_verified       BOOLEAN      NOT NULL DEFAULT FALSE,
    failed_login_attempts INT         NOT NULL DEFAULT 0,
    locked_until         TIMESTAMPTZ,
    created_at           TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at           TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    terms_accepted_at    TIMESTAMPTZ NOT NULL
);

-- Case-insensitive uniqueness for e-mail
CREATE UNIQUE INDEX uq_users_email_ci ON users (LOWER(email));

-- Email verification tokens ---------------------------------------
CREATE TABLE email_verification_tokens (
    token       UUID PRIMARY KEY,
    user_id     UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    expires_at  TIMESTAMPTZ NOT NULL,
    used        BOOLEAN      NOT NULL DEFAULT FALSE,
    created_at  TIMESTAMPTZ  NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_email_verif_expires ON email_verification_tokens (expires_at);
