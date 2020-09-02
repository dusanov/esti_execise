package me.dusanov.unida.cbs.products.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("product_item")
public class ProductItem {
	@Id
	private Integer id;
	private String title;
	private String description;
	private Double price;
	//TODO: investigate Mono<ProductCategory>
	//private ProductCategory category;
	@Column("category_id")
	private Integer categoryId;
}
