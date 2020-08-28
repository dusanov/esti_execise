import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Article } from '../Article';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ArticlesService {

  //private readonly URL = 'http://localhost:8080/articles';
  private readonly URL = 'api/articles';

  constructor(private http: HttpClient) { }

  getAllArticles(): Observable<Article[]> {
    console.log('Request is sent!');
    console.log(this.URL);
    return this.http.get<Article[]>(this.URL).pipe(
        tap(_ => console.log('Articles fetched !'))
    );
  }

  getArticle(id: number): Observable<Article> {
    const url = `${this.URL}/${id}`;
    console.log(`Request for article id: ${id} is sent!`);
    console.log(url);
    return this.http.get<Article>(url).pipe(

      tap(_ => console.log(`fetched article = ${id}`))

    );
  }
}
