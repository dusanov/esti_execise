import { Component, OnInit, Input } from '@angular/core';
import { Article } from '../Article';
import { ArticlesService} from '../service/articles.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-article-detail',
  templateUrl: './article-detail.component.html',
  styleUrls: ['./article-detail.component.css']
})
export class ArticleDetailComponent implements OnInit {

  @Input() article: Article;

  constructor(
    private route: ActivatedRoute,
    private articlesService: ArticlesService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getArticle();
  }

  getArticle(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.articlesService.getArticle(id)
      .subscribe(article => this.article = article);
  }

  save(): void {
    this.articlesService.updateArticle(this.article)
      .subscribe(/*() => this.goBack()*/);
  }

}
