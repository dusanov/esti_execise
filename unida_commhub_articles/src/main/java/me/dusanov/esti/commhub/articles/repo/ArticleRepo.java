package me.dusanov.esti.commhub.articles.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import me.dusanov.esti.commhub.articles.model.Article;

@Repository
public interface ArticleRepo extends ReactiveCrudRepository<Article, Integer> {}
