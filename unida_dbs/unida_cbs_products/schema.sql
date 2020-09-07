CREATE TABLE product_category (
	id serial PRIMARY KEY,
	title VARCHAR (50) NOT NULL,
	description VARCHAR (255) NOT NULL
);

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

CREATE TABLE product_package (
	id serial PRIMARY KEY,
	title VARCHAR (50) NOT NULL,
	description VARCHAR (255) NOT NULL,
	price NUMERIC (10,2)
);

CREATE TABLE product_package_items (
	package_id INTEGER,
	item_id INTEGER,
	PRIMARY KEY(package_id, item_id),
	CONSTRAINT fk_product_package
		FOREIGN KEY (package_id)
			REFERENCES product_package(id),
	CONSTRAINT fk_product_item
		FOREIGN KEY (item_id)
			REFERENCES product_item(id)
);