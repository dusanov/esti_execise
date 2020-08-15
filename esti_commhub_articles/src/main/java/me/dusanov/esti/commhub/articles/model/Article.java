package me.dusanov.esti.commhub.articles.model;

import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("articles")
public class Article {
	
	private Integer articleId;
	private String articleTitle;
	private String articleDesc;
	private int article_type;
	private String articleText;
	private String articleImage;
	/*
	created_by INT,
	created_on TIMESTAMP NOT NULL,
    modified_by INT,
	modified_on TIMESTAMP
	 */
}
