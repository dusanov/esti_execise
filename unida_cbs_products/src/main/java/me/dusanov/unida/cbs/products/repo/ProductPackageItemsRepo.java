package me.dusanov.unida.cbs.products.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import me.dusanov.unida.cbs.products.model.ProductPackageItems;
import me.dusanov.unida.cbs.products.model.ProductPackageItemsId;

@Repository
public interface ProductPackageItemsRepo extends ReactiveCrudRepository<ProductPackageItems, ProductPackageItemsId> {}
