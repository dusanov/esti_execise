insert into product_category (title, description) values 
	('Internet','Internet related products'),
	('Landline','Land line telephony'),
	('TV','Television related products'),
	('Mobile','Mobile telephony related products'),
	('Apps','Application products');--,
	--('Packages','Special package products');

insert into product_item (title, description, price, category_id) values
	('10mbit','10mbit/s internet package', 10.05, (select pc.id
	from product_category as pc where pc.title = 'Internet')),
	('50mbit','50mbit/s internet package', 45.55, (select pc.id
	from product_category as pc where pc.title = 'Internet')),
	('100mbit','100mbit/s internet package', 90.05, (select pc.id
	from product_category as pc where pc.title = 'Internet')),
	('Phone Line','Phone device and land line', 30.35, (select pc.id
	from product_category as pc where pc.title = 'Landline')),
	('Sport TV','Sport TV channels', 45.35, (select pc.id
	from product_category as pc where pc.title = 'TV')),
	('Music TV','Music TV channels', 45.35, (select pc.id
	from product_category as pc where pc.title = 'TV')),
	('Pron TV','Pron TV channels', 65.35, (select pc.id
	from product_category as pc where pc.title = 'TV')),
	('Nokia 3030','Fancy nokia', 15.35, (select pc.id
	from product_category as pc where pc.title = 'Mobile')),
	('10GB Data','10GB od Data plan', 15.05, (select pc.id
	from product_category as pc where pc.title = 'Mobile')),
	('20GB Data','20GB od Data plan', 25.05, (select pc.id
	from product_category as pc where pc.title = 'Mobile')),
	('Covid app','Track your Covid neighbours', 0.0, (select pc.id
	from product_category as pc where pc.title = 'Apps'));
	
insert into product_package (title, description, price) values
	('Bronze', 'Basic internet, phone line and TV', 120.00),
	('Silver', 'Medium internet, phone line and TV', 200),
	('Gold', 'Fast internet, phone line, all TV and Nokia 3030', 300.99);
	
insert into product_package_items (package_id, item_id) values
	((select id from product_package where title = 'Bronze'),
		(select id from product_item where title = '10mbit')),
	((select id from product_package where title = 'Bronze'),
		(select id from product_item where title = 'Phone Line')),
	((select id from product_package where title = 'Bronze'),
		(select id from product_item where title = 'Sport TV')),

	((select id from product_package where title = 'Silver'),
		(select id from product_item where title = '50mbit')),
	((select id from product_package where title = 'Silver'),
		(select id from product_item where title = 'Phone Line')),
	((select id from product_package where title = 'Silver'),
		(select id from product_item where title = 'Music TV'));