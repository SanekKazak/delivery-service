-- liquibase formatted sql

-- changeset kazak:1761494321667-1
CREATE TABLE user_roles
(
    id      UUID NOT NULL,
    name    VARCHAR(255),
    created TIMESTAMP WITHOUT TIME ZONE,
    updated TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_user_roles PRIMARY KEY (id)
);

-- changeset kazak:1761494321667-2
ALTER TABLE user_roles
    ADD CONSTRAINT uc_user_roles_name UNIQUE (name);

-- changeset kazak:1761494311782-5
ALTER TABLE user_details_roles
    ADD CONSTRAINT fk_usedetrol_on_user_details_entity FOREIGN KEY (users_id) REFERENCES user_details (id);

-- changeset kazak:1761494311782-6
ALTER TABLE user_details_roles
    ADD CONSTRAINT fk_usedetrol_on_user_role_entity FOREIGN KEY (roles_id) REFERENCES user_roles (id);