import { ProductCategory } from './product-category'

export interface ProductItem {
  id: number;
  title: string;
  description: string;
  price: number;
  category: ProductCategory;
}
