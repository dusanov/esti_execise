package me.dusanov.unida.cbs.products.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.dusanov.unida.cbs.products.model.ProductItem;
import me.dusanov.unida.cbs.products.service.ProductItemService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

	private final ProductItemService productService;

  	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProductItem> create(@RequestBody ProductItem product){
	  	return productService.create(product);
    }

    @GetMapping
    public Flux<ProductItem> getAll(){
    	return productService.getAll();
    }

    @GetMapping("/{productId}")
    public Mono<ResponseEntity<ProductItem>> getProductById(@PathVariable Integer productId){
        Mono<ProductItem> Product = productService.findById(productId);
        return Product.map( product -> ResponseEntity.ok(product))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{productId}")
    public Mono<ResponseEntity<ProductItem>> updateProductById(@PathVariable Integer productId, @RequestBody ProductItem product){
    	log.debug(" === got: " + product  + ", product id:  " + productId);
        return productService.update(productId,product)
                .map(updatedProduct -> ResponseEntity.ok(updatedProduct))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{productId}")
    public Mono<ResponseEntity<Void>> deleteProductById(@PathVariable Integer productId){
        return productService.delete(productId)
                .map( r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/search/id")
    public Flux<ProductItem> fetchProductsByIds(@RequestBody List<Integer> ids) {
    	return Flux.just(new ProductItem());
    }

}
