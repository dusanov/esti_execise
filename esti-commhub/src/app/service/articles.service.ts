import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ArticlesService {

  private readonly URL = 'http://localhost:8080/articles';

  constructor(private http: HttpClient) { }

  resolveItems(): Observable<any> {
    console.log('Request is sent!');
    return this.http.get(this.URL);
  }
}