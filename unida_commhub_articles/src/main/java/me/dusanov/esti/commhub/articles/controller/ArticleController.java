package me.dusanov.esti.commhub.articles.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.dusanov.esti.commhub.articles.model.Article;
import me.dusanov.esti.commhub.articles.service.ArticleService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ArticleController {
	
	private final ArticleService articleService;	
	
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Article> create(@RequestBody Article article){
		return articleService.createArticle(article);
    }

    @GetMapping
    public Flux<Article> getAllArticles(){
    	return articleService.getAllArticles();
    }

    @GetMapping("/{articleId}")
    public Mono<ResponseEntity<Article>> getArticleById(@PathVariable Integer articleId){
        Mono<Article> Article = articleService.findById(articleId);
        return Article.map( article -> ResponseEntity.ok(article))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{articleId}")
    public Mono<ResponseEntity<Article>> updateArticleById(@PathVariable Integer articleId, @RequestBody Article article){
    	log.debug(" === got: " + article  + ", article id:  " + articleId);
        return articleService.updateArticle(articleId,article)
                .map(updatedArticle -> ResponseEntity.ok(updatedArticle))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{articleId}")
    public Mono<ResponseEntity<Void>> deleteArticleById(@PathVariable Integer articleId){
        return articleService.deleteArticle(articleId)
                .map( r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/search/id")
    public Flux<Article> fetchArticlesByIds(@RequestBody List<Integer> ids) {
    	return Flux.just(new Article());
    }	

}
