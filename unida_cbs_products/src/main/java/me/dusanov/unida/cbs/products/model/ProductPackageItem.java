package me.dusanov.unida.cbs.products.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("product_package_items")
//@IdClass(ProductPackageItemsId.class)
public class ProductPackageItem {
	//@Id	private Integer packageId;
	//@Id	private Integer itemId;
	@Id private ProductPackageItemId packageItemId;
}
