package me.dusanov.esti.commhub.articles.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import io.r2dbc.spi.Row;
import lombok.extern.slf4j.Slf4j;
import me.dusanov.esti.commhub.articles.model.Article;

@Slf4j
@ReadingConverter
public class ArticleReadConverter implements Converter<Row, Article> {

	public Article convert(Row source) {
		log.info(" === about to convert: " + source);
		return new Article(source.get("article_id",Integer.class),
							source.get("article_title",String.class),
							source.get("article_short_desc", String.class),
							source.get("article_type", Integer.class),
							source.get("article_text",String.class), //TODO: this is CLOB
							source.get("article_image",String.class));
	}
	
	

}
