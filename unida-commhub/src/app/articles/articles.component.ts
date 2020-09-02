import { Component, OnInit, Input } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { ArticlesService} from '../service/articles.service';
import { Article } from '../Article';
import { Router } from '@angular/router';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.css']
})
export class ArticlesComponent implements OnInit {

  @Input() articles: Article[]; //Observable<Article[]>;

  getArticles(): void {
    this.articlesService.getAllArticles()
      .subscribe( articles => this.articles = articles);
  }

  constructor(
    private articlesService: ArticlesService,
    private router: Router) {}

  ngOnInit(): void {
    this.getArticles();
  }

  new(): void{ 
    console.log(" new !");
    this.router.navigate(["/article"]);
  }
  edit(): void{ console.log(" edit !") }

  delete( article: Article ): void{ 
    console.log(" delete !") 
    this.articlesService.deleteArticle(article)
    .subscribe( _ => {
        console.log('Article deleted !');
        this.getArticles();
      },
      catchError(this.handleError<Article>('deleteArticles',article))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
     console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

}
