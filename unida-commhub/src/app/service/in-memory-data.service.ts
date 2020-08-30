import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api';
import { Article } from '../Article';

@Injectable({
  providedIn: 'root'
})
export class InMemoryDataService implements InMemoryDbService {

  createDb() {
    const articles = <Article[]>[
      { id: 1, articleTitle: 'Dr Nice 1', articleDesc: 'desc 1', articleType: 0, articleText:'text 1', articleImage:'' },
      { id: 2, articleTitle: 'Dr Nice 2', articleDesc: 'desc 2', articleType: 0, articleText:'text 2', articleImage:'' },
      { id: 3, articleTitle: 'Dr Nice 3', articleDesc: 'desc 3', articleType: 0, articleText:'text 3', articleImage:'' },
      { id: 4, articleTitle: 'Dr Nice 4', articleDesc: 'desc 4', articleType: 0, articleText:'text 4', articleImage:'' },
      { id: 5, articleTitle: 'Dr Nice 5', articleDesc: 'desc 5', articleType: 0, articleText:'text 5', articleImage:'' }
    ];
    return {articles};
  }

  genId(articles: Article[]): number {
    return articles.length > 0 ? Math.max(...articles.map(article => article.id)) + 1 : 11;
  }

  getNewArticle(): Article {
    //const newId = this.genId(this.createDb());
    return <Article>{/*id:newId*/};
  }

  constructor() {}
}
