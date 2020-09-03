package me.dusanov.unida.cbs.products.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import lombok.RequiredArgsConstructor;
import me.dusanov.unida.cbs.products.model.ProductCategory;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Disabled
@RequiredArgsConstructor
public class ProductCategoryServiceTest {

	private final ProductCategoryService service;
	
	@Test
	public void testCreateCategory() {
		
		Mono<ProductCategory> createSource = service.create(new ProductCategory(null,"Title1","Desc1"));
		
		StepVerifier.create(createSource)
		//expect that id is not null
		.expectNextMatches(predicate -> predicate.getDescription().equalsIgnoreCase("desc1"))
		.verifyComplete();
	}
	
}
