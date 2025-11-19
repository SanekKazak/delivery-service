-- liquibase formatted sql

-- changeset kazak:1760956622951-1
CREATE TABLE cards
(
    id            UUID NOT NULL,
    token         UUID NOT NULL,
    pan_end       VARCHAR(255),
    card_supplier VARCHAR(255),
    user_id       UUID,
    CONSTRAINT pk_cards PRIMARY KEY (id)
);

-- changeset kazak:1760956622951-2
ALTER TABLE cards
    ADD CONSTRAINT FK_CARDS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

