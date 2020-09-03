package me.dusanov.unida.cbs.products.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.dusanov.unida.cbs.products.model.ProductItem;
import me.dusanov.unida.cbs.products.repo.ProductItemRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
//@Slf4j
@RequiredArgsConstructor
public class ProductItemService {
	
	private final ProductItemRepo itemRepo;
	
	public Mono<ProductItem> create(ProductItem item){
		return itemRepo.save(item);
	}
	
	public Flux<ProductItem> getAll(){
		return itemRepo.findAll();
	}
	
	public Mono<ProductItem> findById(Integer itemId){
		return itemRepo.findById(itemId);
	}
	
	public Mono<ProductItem> update(Integer itemId, ProductItem item){
		return itemRepo.findById(itemId)
				.flatMap(dbItem -> {
					dbItem.setTitle(item.getTitle());
					dbItem.setDescription(item.getDescription());
					return itemRepo.save(dbItem);
				});
	}
	
	public Mono<ProductItem> delete(Integer itemId){
		return itemRepo.findById(itemId)
				.flatMap(dbItem -> itemRepo.delete(dbItem)
				.then(Mono.just(dbItem)));
	}	
}
