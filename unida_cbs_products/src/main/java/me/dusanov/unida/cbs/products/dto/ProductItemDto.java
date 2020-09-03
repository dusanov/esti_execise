package me.dusanov.unida.cbs.products.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.dusanov.unida.cbs.products.model.ProductCategory;
import me.dusanov.unida.cbs.products.model.ProductItem;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class ProductItemDto {
//	private Integer id;
//	private String title;
//	private String description;
//	private Double price;
	private ProductItem item;
	private ProductCategory category;
	
	public ProductItemDto (ProductItem item, ProductCategory category) {
//		this.id = item.getId();
//		this.title = item.getTitle();
//		this.description = item.getDescription();
//		this.price = item.getPrice();
		this.item = item;
		this.category = category;
	}
}
