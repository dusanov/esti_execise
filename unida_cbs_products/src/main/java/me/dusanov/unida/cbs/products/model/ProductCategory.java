package me.dusanov.unida.cbs.products.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("product_category")
public class ProductCategory {
	@Id
	private Integer id;
	private String title;
	private String description;
}
