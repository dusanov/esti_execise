import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api';
import { Article } from '../Article';
import { ProductCategory } from '../product-category';
import { ProductItem } from '../product-item';
import { ProductPackage } from '../product-package';

@Injectable({
  providedIn: 'root'
})
export class InMemoryDataService implements InMemoryDbService {

  createDb() {
    let articles = <Article[]>[
      { id: 1, articleTitle: 'Dr Nice 1', articleDesc: 'desc 1', articleType: 0, articleText:'text 1', articleImage:'' },
      { id: 2, articleTitle: 'Dr Nice 2', articleDesc: 'desc 2', articleType: 0, articleText:'text 2', articleImage:'' },
      { id: 3, articleTitle: 'Dr Nice 3', articleDesc: 'desc 3', articleType: 0, articleText:'text 3', articleImage:'' },
      { id: 4, articleTitle: 'Dr Nice 4', articleDesc: 'desc 4', articleType: 0, articleText:'text 4', articleImage:'' },
      { id: 5, articleTitle: 'Dr Nice 5', articleDesc: 'desc 5', articleType: 0, articleText:'text 5', articleImage:'' }
    ];

    let productCategories = <ProductCategory[]>[
      {id: 1, title: 'Internet', description: 'Internet related products'},
      {id: 2, title: 'Mobile', description: 'Mobile telephony related products'}
    ];

    let productItems = <ProductItem[]>[
      {id: 1, title: '10mbit', description: '10mbit internet', price: 10.05, category: {id:1,title:'Internet', description:'Internet stuff'}},
      {id: 2, title: 'Nokia 3030', description: 'Amazing nokia', price: 110.05, category: {id:2,title:'Mobile',descriptio:'Mobile stuff'}}
    ];

    let productPackages = <ProductPackages[]>[
      {id: 1, title: 'Bronze', description: 'Basic package', price:110.00, items: [
        {id:1,title:'10mbit',description:'10mbit internet',price:null, category: {id:1,title:'Internet',description:'Internet stuff'}}
      ]},
      {id: 2, title: 'Silver', description: 'A bit fansier package', price: 150.66, items: [
        {id: 1,title: '10mbit',description:'10mbit internet',price: null, category: {id: 1,title:'Internet',description:'Internet stuff'}},
        {id: 2,title: '10mbit',description: '10mbit internet',price: null, category: {id: 2,title: 'Mobile',description:'Mobile stuff'}}
      ]}
    ];

    return {articles: articles, productCategories: productCategories, productItems: productItems/*, productPackages: productPackages */};
  }

  //genId(articles: Article[]): number {
  genId<T extends Article | ProductCategory>(rows: T[]): number{
    //return articles.length > 0 ? Math.max(...articles.map(article => article.id)) + 1 : 11;
    return rows.length > 0 ? Math.max(...rows.map(row => row.id)) + 1 : 11;
  }

  getNewArticle(): Article {
    //const newId = this.genId(this.createDb());
    return <Article>{/*id:newId*/};
  }

  constructor() {}
}
