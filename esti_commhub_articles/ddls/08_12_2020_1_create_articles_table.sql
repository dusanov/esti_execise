-- 08_12_2020_1_create_articles_table.sql

CREATE TABLE articles (
	article_id serial PRIMARY KEY,
	article_title VARCHAR (50) NOT NULL,
	article_short_desc VARCHAR (255) NOT NULL,
	article_type INT,
	article_text TEXT NOT NULL,
	article_image VARCHAR (50),
	created_by INT,
	created_on TIMESTAMP NOT NULL,
    modified_by INT,
	modified_on TIMESTAMP
	--FOREIGN KEY (article_type)
    --  REFERENCES article_types (article_type_id),
	--FOREIGN KEY (created_by)
    --  REFERENCES users (user_id),
	--FOREIGN KEY (modified_by)
    --  REFERENCES users (user_id)    
);