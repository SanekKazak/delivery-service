-- liquibase formatted sql

-- changeset kazak:1761494219532-1
CREATE TABLE orders
(
    id              UUID             NOT NULL,
    restaurant_id   UUID             NOT NULL,
    user_id         UUID             NOT NULL,
    total_price     DOUBLE PRECISION NOT NULL,
    restaurant_name VARCHAR(255),
    created_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at      TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

