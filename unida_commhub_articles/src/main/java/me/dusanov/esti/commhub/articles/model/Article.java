package me.dusanov.esti.commhub.articles.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("articles")
public class Article {
	
	@Id
	@Column("article_id")
	private Integer id;
	private String articleTitle;
	@Column("article_short_desc")
	private String articleDesc;
	private int articleType;
	//todo: this is clob
	private String articleText;
	//private Clob articleText;
	private String articleImage;
	/*
	created_by INT,
	created_on TIMESTAMP NOT NULL,
    modified_by INT,
	modified_on TIMESTAMP
	 */
}
