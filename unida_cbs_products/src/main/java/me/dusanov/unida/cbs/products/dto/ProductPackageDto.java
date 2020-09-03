package me.dusanov.unida.cbs.products.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.dusanov.unida.cbs.products.model.ProductItem;
import me.dusanov.unida.cbs.products.model.ProductPackage;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPackageDto {
	private ProductPackage productPackage;
	private List<ProductItem> items;

}
