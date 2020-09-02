package me.dusanov.unida.commhub.articles.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import me.dusanov.unida.commhub.articles.model.Article;

@Repository
public interface ArticleRepo extends ReactiveCrudRepository<Article, Integer> {}
