import { Component, OnInit, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { ArticlesService} from '../service/articles.service';
import { Article } from '../article';

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

}
