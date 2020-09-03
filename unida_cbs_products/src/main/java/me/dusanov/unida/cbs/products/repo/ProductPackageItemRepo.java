package me.dusanov.unida.cbs.products.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import me.dusanov.unida.cbs.products.model.ProductPackageItem;
import me.dusanov.unida.cbs.products.model.ProductPackageItemId;
import reactor.core.publisher.Flux;

@Repository
public interface ProductPackageItemRepo extends ReactiveCrudRepository<ProductPackageItem, ProductPackageItemId> {
	public Flux<ProductPackageItem> findByPackageItemId(Integer packageId);
}
