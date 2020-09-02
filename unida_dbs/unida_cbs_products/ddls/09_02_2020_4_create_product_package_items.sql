-- 09_02_2020_1_create_product_package_items.sql

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