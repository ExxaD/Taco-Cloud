CREATE TABLE IF NOT EXISTS ingredients (
    id VARCHAR(4) PRIMARY KEY NOT NULL,
    name VARCHAR(25) NOT NULL,
    type VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS tacos (
    id IDENTITY,
    name VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS tacos_ingredients (
    taco_id BIGINT NOT NULL,
    ingredient_id VARCHAR(4) NOT NULL
);

ALTER TABLE tacos_ingredients
    ADD FOREIGN KEY (taco_id) references tacos(id);
ALTER TABLE tacos_ingredients
    ADD FOREIGN KEY (ingredient_id) references ingredients(id);

CREATE TABLE IF NOT EXISTS taco_orders (
    id IDENTITY,
    name VARCHAR(50) NOT NULL,
    street VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(2) NOT NULL,
    zip VARCHAR(10) NOT NULL,
    user_id BIGINT,
    cc_number VARCHAR(16) NOT NULL,
    cc_expiration VARCHAR(5) NOT NULL,
    cc_cvv VARCHAR(3) NOT NULL,
    placed_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS taco_orders_tacos (
    order_id BIGINT NOT NULL,
    taco_id BIGINT NOT NULL
);

ALTER TABLE taco_orders_tacos
    ADD FOREIGN KEY (order_id) REFERENCES taco_orders(id);
ALTER TABLE taco_orders_tacos
    ADD FOREIGN KEY (taco_id) REFERENCES tacos(id);

CREATE TABLE IF NOT EXISTS users (
    id IDENTITY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(60) NOT NULL,
    fullname VARCHAR(50) NOT NULL,
    street VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(2) NOT NULL,
    zip VARCHAR(10) NOT NULL,
    phone_number VARCHAR(15) NOT NULL
);