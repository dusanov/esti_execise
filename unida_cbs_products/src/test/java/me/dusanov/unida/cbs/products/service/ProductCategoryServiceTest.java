package me.dusanov.unida.cbs.products.service;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import me.dusanov.unida.cbs.products.model.ProductCategory;
import me.dusanov.unida.cbs.products.repo.ProductCategoryRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@ContextConfiguration
//@Transactional
//https://github.com/spring-projects/spring-framework/issues/24226
//always run the tests with the -Ptest profile with the in memory database
@ActiveProfiles("test")
//@Sql({"/schema.sql", "/data.sql"})
public class ProductCategoryServiceTest {

	@Autowired private ProductCategoryService service;
	@Autowired private ProductCategoryRepo categoryRepo;
	    
    @Autowired private DatabaseClient databaseClient;
    
    private List<ProductCategory> getData(){
        return Arrays.asList( new ProductCategory(null,"Internet","desc 1"));
    }    
    
    @BeforeEach
    public  void setup(){
    	
    	//webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();
    	
        List<String> statements = Arrays.asList("DROP TABLE IF EXISTS product_category;",
        		"CREATE TABLE product_category (\r\n" + 
        		"	id serial PRIMARY KEY,\r\n" + 
        		"	title VARCHAR (50) NOT NULL,\r\n" + 
        		"	description VARCHAR (255) NOT NULL\r\n" + 
        		")");

        statements.forEach(it -> databaseClient.execute(it)
                .fetch()
                .rowsUpdated()
                .block());

        categoryRepo.deleteAll()
                .thenMany(Flux.fromIterable(getData()))
                .flatMap(categoryRepo::save)
                .doOnNext(row ->{
                    System.out.println("Product category inserted from: " + row);
                })
                .blockLast();

    }	
	
	
	@Test
	public void testFindById() {
		Mono<ProductCategory> readSource = service.findById(1);
		StepVerifier.create(readSource)
		.expectNextMatches(category -> category.getTitle().equals("Internet"))
		.verifyComplete();
	}
	
	
	@Test
	public void testCreateCategorySuccess() {
		Mono<ProductCategory> createSource = service.create(new ProductCategory(null,"Title1","Desc1"));
		StepVerifier.create(createSource)
		//expect that id is not null
		.expectNextMatches(category -> category.getId() != null)
		.verifyComplete();
	}
	
	@Test
	public void testCreateCategoryFail() {
		Mono<ProductCategory> createSource = service.create(new ProductCategory(null,"Title1","Desc1"));
		StepVerifier.create(createSource)		
		.expectNextMatches(category -> category.getDescription().equals("someting else"))
		.expectError(java.lang.AssertionError.class);
	}
}