package me.dusanov.unida.cbs.products.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import me.dusanov.unida.cbs.products.model.ProductCategory;

@Repository
public interface ProductCategoryRepo extends ReactiveCrudRepository<ProductCategory, Integer>{}
