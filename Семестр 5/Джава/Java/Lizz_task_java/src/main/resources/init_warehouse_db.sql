-- Создание таблицы для товаров на складе
CREATE TABLE IF NOT EXISTS product
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255),
    unit_price  DECIMAL
    );

-- Создание таблицы для поставщиков
CREATE TABLE IF NOT EXISTS supplier
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(255),
    email VARCHAR(255)
    );

-- Создание таблицы для заказов от поставщиков
CREATE TABLE IF NOT EXISTS supply_order
(
    id           SERIAL PRIMARY KEY,
    supplier_id  BIGINT REFERENCES supplier (id) ON DELETE CASCADE
    );

-- Создание таблицы для элементов заказов на поставку
CREATE TABLE IF NOT EXISTS supply_order_item
(
    id              SERIAL PRIMARY KEY,
    supply_order_id BIGINT REFERENCES supply_order (id) ON DELETE CASCADE,
    product_id      BIGINT REFERENCES product (id) ON DELETE CASCADE,
    quantity        INT
    );

-- Вставка данных в таблицу товаров
INSERT INTO product (name, unit_price)
VALUES ('Laptop', 799.99);

INSERT INTO product (name, unit_price)
VALUES ('Smartphone', 599.49);

-- Вставка данных в таблицу поставщиков
INSERT INTO supplier (name, email)
VALUES ('Tech Supplies Inc.', 'techsupplies@example.com');

INSERT INTO supplier (name, email)
VALUES ('Gadget World', 'gadgetworld@example.com');

-- Заказ от поставщика с id = 1 (Tech Supplies Inc.)
INSERT INTO supply_order (supplier_id)
VALUES (1);

-- Заказ от поставщика с id = 2 (Gadget World)
INSERT INTO supply_order (supplier_id)
VALUES (2);

-- Элементы заказа для заказа с id = 1 (Tech Supplies Inc.)
INSERT INTO supply_order_item (supply_order_id, product_id, quantity)
VALUES (1, 1, 10); -- 10 штук Laptop от Tech Supplies Inc.

INSERT INTO supply_order_item (supply_order_id, product_id, quantity)
VALUES (1, 2, 20); -- 20 штук Smartphone от Tech Supplies Inc.

-- Элементы заказа для заказа с id = 2 (Gadget World)
INSERT INTO supply_order_item (supply_order_id, product_id, quantity)
VALUES (2, 1, 5); -- 5 штук Laptop от Gadget World

INSERT INTO supply_order_item (supply_order_id, product_id, quantity)
VALUES (2, 2, 15); -- 15 штук Smartphone от Gadget World