package me.dusanov.unida.cbs.products.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.dusanov.unida.cbs.products.model.ProductCategory;
import me.dusanov.unida.cbs.products.repo.ProductCategoryRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
//@Slf4j
@RequiredArgsConstructor
public class ProductCategoryService {
	
	private final ProductCategoryRepo categoryRepo;
	
	public Mono<ProductCategory> create(ProductCategory category){
		return categoryRepo.save(category);
	}
	
	public Flux<ProductCategory> getAll(){
		return categoryRepo.findAll();
	}
	
	public Mono<ProductCategory> findById(Integer categoryId){
		return categoryRepo.findById(categoryId);
	}
	
	public Mono<ProductCategory> update(Integer categoryId, ProductCategory category){
		return categoryRepo.findById(categoryId)
				.flatMap(dbCategory -> {
					dbCategory.setTitle(category.getTitle());
					dbCategory.setDescription(category.getDescription());
					return categoryRepo.save(dbCategory);
				});
	}
	
	public Mono<ProductCategory> delete(Integer categoryId){
		return categoryRepo.findById(categoryId)
				.flatMap(dbCategory -> categoryRepo.delete(dbCategory)
				.then(Mono.just(dbCategory)));
	}	
}
