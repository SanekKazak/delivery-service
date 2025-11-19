-- liquibase formatted sql

-- changeset kazak:1760956609463-1
CREATE TABLE users
(
    id         UUID         NOT NULL,
    login      VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

-- changeset kazak:1760956609463-2
ALTER TABLE users
    ADD CONSTRAINT uc_users_login UNIQUE (login);

