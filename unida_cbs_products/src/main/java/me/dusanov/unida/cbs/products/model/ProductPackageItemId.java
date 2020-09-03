package me.dusanov.unida.cbs.products.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Embeddable
public class ProductPackageItemId implements Serializable {

	private static final long serialVersionUID = -2198471284740488808L;
	
	private Integer packageId;
	private Integer itemId;

}
