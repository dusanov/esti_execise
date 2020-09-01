import { Component, OnInit, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { ArticlesService} from '../service/articles.service';
import { Article } from '../Article';

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

  constructor(private articlesService: ArticlesService) {}

  ngOnInit(): void {
    this.getArticles();
  }

  new(): void{ console.log(" new !") }
  edit(): void{ console.log(" edit !") }
  delete(): void{ console.log(" delete !") }

}
