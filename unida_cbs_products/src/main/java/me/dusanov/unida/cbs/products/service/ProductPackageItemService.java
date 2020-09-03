package me.dusanov.unida.cbs.products.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.dusanov.unida.cbs.products.model.ProductPackageItem;
import me.dusanov.unida.cbs.products.model.ProductPackageItemId;
import me.dusanov.unida.cbs.products.repo.ProductPackageItemRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
//@Slf4j
@RequiredArgsConstructor
public class ProductPackageItemService {
	
	private final ProductPackageItemRepo packageItemsRepo;
	
	public Mono<ProductPackageItem> create(ProductPackageItem packageItem){
		return packageItemsRepo.save(packageItem);
	}
	
	public Flux<ProductPackageItem> getAll(){
		return packageItemsRepo.findAll();
	}
	
	public Mono<ProductPackageItem> findById(ProductPackageItemId packageItemId){
		return packageItemsRepo.findById(packageItemId);
	}
	
	public Flux<ProductPackageItem> findByPackageId(ProductPackageItemId packageItemId){
		return packageItemsRepo.findByPackageItemId(packageItemId.getPackageId());
	}
	
	public Mono<ProductPackageItem> update(ProductPackageItemId packageItemId,ProductPackageItemId newPackageItemId){
		return packageItemsRepo.findById(packageItemId)
				.flatMap(dbPackageItem -> {
					dbPackageItem.setPackageItemId(newPackageItemId);
					return packageItemsRepo.save(dbPackageItem);
				});
	}
	
	public Mono<ProductPackageItem> delete(ProductPackageItemId packageItemId){
		return packageItemsRepo.findById(packageItemId)
				.flatMap(dbPackage -> packageItemsRepo.delete(dbPackage)
				.then(Mono.just(dbPackage)));
	}	
}
