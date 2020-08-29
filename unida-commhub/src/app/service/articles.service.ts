import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Article } from '../Article';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})

export class ArticlesService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  //private readonly URL = 'http://localhost:8080/articles';
  private readonly URL = 'api/articles';

  constructor(private http: HttpClient) { }
  /** PUT: update the article on the server */
  updateArticle(article: Article): Observable<any> {
    return this.http.put(this.URL, article, this.httpOptions).pipe(
      tap(_ => console.log(`updated article id=${article.id}`)),
      catchError(this.handleError<any>('updateArticle'))
    );
  }

  getAllArticles(): Observable<Article[]> {
    return this.http.get<Article[]>(this.URL).pipe(
        tap(_ => console.log('Articles fetched !')),
        catchError(this.handleError<Article[]>('getAllArticles',[]))
    );
  }

  getArticle(id: number): Observable<Article> {
    const url = `${this.URL}/${id}`;
    return this.http.get<Article>(url).pipe(
      tap(_ => console.log(`fetched article = ${id}`)),
      catchError(this.handleError<Article>(`getArticle id=${id}`,<Article>{}))
    );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
 */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
     console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
