-- liquibase formatted sql

-- changeset kazak:1761494230922-1
CREATE TABLE order_items
(
    id                 UUID             NOT NULL,
    restaurant_item_id UUID             NOT NULL,
    order_id           UUID             NOT NULL,
    quantity           INTEGER          NOT NULL,
    price              DOUBLE PRECISION NOT NULL,
    name               VARCHAR(255),
    CONSTRAINT pk_order_items PRIMARY KEY (id)
);

-- changeset kazak:1761494230922-2
ALTER TABLE order_items
    ADD CONSTRAINT FK_ORDER_ITEMS_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);

