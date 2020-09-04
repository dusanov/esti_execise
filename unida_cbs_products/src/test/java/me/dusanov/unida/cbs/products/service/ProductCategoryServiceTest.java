package me.dusanov.unida.cbs.products.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import me.dusanov.unida.cbs.products.model.ProductCategory;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@ContextConfiguration
//@Transactional
//https://github.com/spring-projects/spring-framework/issues/24226
//always run the tests with the -Ptest profile with the in memory database
@ActiveProfiles("test")
public class ProductCategoryServiceTest {

	@Autowired
	private ProductCategoryService service;
	
	@Test
	
	public void testCreateCategory() {
		
		Mono<ProductCategory> createSource = service.create(new ProductCategory(null,"Title1","Desc1"));
		
		StepVerifier.create(createSource)
		//expect that id is not null
		.expectNextMatches(category -> category.getDescription().equalsIgnoreCase("desc1"))
		.expectComplete();
		//.verifyComplete();
	}
	
}