-- liquibase formatted sql

-- changeset kazak:1761494311782-1
CREATE TABLE user_details
(
    id           UUID NOT NULL,
    reference_id UUID,
    login        VARCHAR(255),
    password     VARCHAR(255),
    CONSTRAINT pk_user_details PRIMARY KEY (id)
);

-- changeset kazak:1761494311782-2
CREATE TABLE user_details_roles
(
    roles_id UUID NOT NULL,
    users_id UUID NOT NULL
);

-- changeset kazak:1761494311782-3
ALTER TABLE user_details
    ADD CONSTRAINT uc_user_details_login UNIQUE (login);

-- changeset kazak:1761494311782-4
ALTER TABLE user_details
    ADD CONSTRAINT uc_user_details_referenceid UNIQUE (reference_id);



