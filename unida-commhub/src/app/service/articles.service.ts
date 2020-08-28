import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Article } from '../Article';

@Injectable({
  providedIn: 'root'
})
export class ArticlesService {

  private readonly URL = 'http://localhost:8080/articles';

  constructor(private http: HttpClient) { }

  getAllArticles(): Observable<Article[]> {
    console.log('Request is sent!');
    return this.http.get<Article[]>(this.URL);
  }

  getArticle(id: number): Observable<Article> {
    const url = `${this.URL}/${id}`;
    return this.http.get<Article>(this.URL);
  }
}
