package me.dusanov.unida.cbs.products.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import me.dusanov.unida.cbs.products.model.ProductPackage;

@Repository
public interface ProductPackageRepo extends ReactiveCrudRepository<ProductPackage, Integer> {}
