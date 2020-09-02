-- 09_02_2020_1_create_product_category.sql

CREATE TABLE product_category (
	id serial PRIMARY KEY,
	title VARCHAR (50) NOT NULL,
	description VARCHAR (255) NOT NULL
);