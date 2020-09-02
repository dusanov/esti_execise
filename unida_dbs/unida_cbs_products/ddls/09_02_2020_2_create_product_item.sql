-- 09_02_2020_1_create_product_item.sql

CREATE TABLE product_item (
	id serial PRIMARY KEY,
	title VARCHAR (50) NOT NULL,
	description VARCHAR (255) NOT NULL,
	price NUMERIC (10,2),
	category_id INTEGER,
	CONSTRAINT fk_product_category
		FOREIGN KEY (category_id)
			REFERENCES product_category(id)
);