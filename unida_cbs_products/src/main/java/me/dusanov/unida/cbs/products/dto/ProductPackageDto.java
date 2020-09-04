package me.dusanov.unida.cbs.products.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.dusanov.unida.cbs.products.model.ProductItem;
import me.dusanov.unida.cbs.products.model.ProductPackage;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class ProductPackageDto {
	private Integer id;
	private String title;
	private String description;
	private Double price;
	private List<ProductItem> items;

	public ProductPackageDto(ProductPackage productPackage, List<ProductItem> items) {
		this.id = productPackage.getId();
		this.title = productPackage.getTitle();
		this.description = productPackage.getDescription();
		this.price = productPackage.getPrice();
		this.items = items;
	}
}