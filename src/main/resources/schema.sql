CREATE TABLE IF NOT EXISTS Ingredient (
    id VARCHAR(4) PRIMARY KEY NOT NULL,
    name VARCHAR(25) NOT NULL,
    type VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS Taco (
    id IDENTITY,
    name VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS Taco_Ingredients (
    taco_id BIGINT NOT NULL,
    ingredients_id VARCHAR(4) NOT NULL
);

ALTER TABLE Taco_Ingredients
    ADD FOREIGN KEY (taco_id) references Taco(id);
ALTER TABLE Taco_Ingredients
    ADD FOREIGN KEY (ingredients_id) references Ingredient(id);

CREATE TABLE IF NOT EXISTS Taco_Order (
    id IDENTITY,
    name VARCHAR(50) NOT NULL,
    street VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(2) NOT NULL,
    zip VARCHAR(10) NOT NULL,
    cc_number VARCHAR(16) NOT NULL,
    cc_expiration VARCHAR(5) NOT NULL,
    cc_cvv VARCHAR(3) NOT NULL,
    placed_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS Taco_Order_Tacos (
    order_id BIGINT NOT NULL,
    tacos_id BIGINT NOT NULL
);

ALTER TABLE Taco_Order_Tacos
    ADD FOREIGN KEY (order_id) REFERENCES Taco_Order(id);
ALTER TABLE Taco_Order_Tacos
    ADD FOREIGN KEY (tacos_id) REFERENCES Taco(id);