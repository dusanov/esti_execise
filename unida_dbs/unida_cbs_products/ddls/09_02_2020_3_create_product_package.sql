-- 09_02_2020_1_create_product_package.sql

CREATE TABLE product_package (
	id serial PRIMARY KEY,
	title VARCHAR (50) NOT NULL,
	description VARCHAR (255) NOT NULL,
	price NUMERIC (10,2)
);