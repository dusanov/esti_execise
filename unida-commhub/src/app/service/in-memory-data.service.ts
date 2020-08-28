import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api';
import { Article } from '../Article';

@Injectable({
  providedIn: 'root'
})
export class InMemoryDataService implements InMemoryDbService {

  createDb() {
    const articles = <Article[]>[
      { articleId: 1, articleTitle: 'Dr Nice 1', articleDesc: 'desc 1', articleType: 0, articleText:'text 1', articleImage:'' },
      { articleId: 2, articleTitle: 'Dr Nice 2', articleDesc: 'desc 2', articleType: 0, articleText:'text 2', articleImage:'' },
      { articleId: 3, articleTitle: 'Dr Nice 3', articleDesc: 'desc 3', articleType: 0, articleText:'text 3', articleImage:'' },
      { articleId: 4, articleTitle: 'Dr Nice 4', articleDesc: 'desc 4', articleType: 0, articleText:'text 4', articleImage:'' },
      { articleId: 5, articleTitle: 'Dr Nice 5', articleDesc: 'desc 5', articleType: 0, articleText:'text 5', articleImage:'' }
    ];
    return {articles};
  }

  // Overrides the genId method to ensure that a article always has an id.
  // If the articles array is empty,
  // the method below returns the initial number (11).
  // if the articles array is not empty, the method below returns the highest
  // article id + 1.
  genId(articles: Article[]): number {
    return articles.length > 0 ? Math.max(...articles.map(article => article.articleId)) + 1 : 11;
  }

  constructor() { }
}
