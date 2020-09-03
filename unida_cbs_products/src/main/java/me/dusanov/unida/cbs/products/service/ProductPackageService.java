package me.dusanov.unida.cbs.products.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.dusanov.unida.cbs.products.model.ProductPackage;
import me.dusanov.unida.cbs.products.repo.ProductPackageRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
//@Slf4j
@RequiredArgsConstructor
public class ProductPackageService {
	
	private final ProductPackageRepo packageRepo;
	
	public Mono<ProductPackage> create(ProductPackage productPackage){
		return packageRepo.save(productPackage);
	}
	
	public Flux<ProductPackage> getAll(){
		return packageRepo.findAll();
	}
	
	public Mono<ProductPackage> findById(Integer productPackageId){
		return packageRepo.findById(productPackageId);
	}
	
	public Mono<ProductPackage> update(Integer productPackageId, ProductPackage productPackage){
		return packageRepo.findById(productPackageId)
				.flatMap(dbPackage -> {
					dbPackage.setTitle(productPackage.getTitle());
					dbPackage.setDescription(productPackage.getDescription());
					return packageRepo.save(dbPackage);
				});
	}
	
	public Mono<ProductPackage> delete(Integer productPackageId){
		return packageRepo.findById(productPackageId)
				.flatMap(dbPackage -> packageRepo.delete(dbPackage)
				.then(Mono.just(dbPackage)));
	}	
}
