import { ProductItem } from './product-item'

export interface ProductPackage {
  id: number;
  title: string;
  description: string;
  price: number;
  items: {[key: number]: ProductItem};
}
