import { Component, OnInit, Input } from '@angular/core';
import { Article } from '../Article';
import { ArticlesService} from '../service/articles.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { State } from './State';
import { FormBuilder, Validators, FormArray } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-article-detail',
  templateUrl: './article-detail.component.html',
  styleUrls: ['./article-detail.component.css']
})
export class ArticleDetailComponent implements OnInit {

  @Input() article: Article;

  articleForm = this.fb.group({
    id: [],
    articleTitle: ['', Validators.required],
    articleDesc:[''],
    articleType:[0],
    articleText:[''],
    articleImage:['']
  });

  public StateEnum = State;
  public mode = State.view;

  constructor(
    private route: ActivatedRoute,
    private articlesService: ArticlesService,
    private location: Location,
    private fb: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getArticle();
  }

  getArticle(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.articlesService.getArticle(id)
      .subscribe(article => this.article = article);
  }

  new(): void{
    //this.article = <Article>{};
    this.articleForm.patchValue({
      id: null,
      articleTitle: '',
      articleDesc: '',
      articleType: '',
      articleText: '',
      articleImage: ''
    });

    this.mode = State.edit;
  }

  edit(): void{

    this.articleForm.patchValue({
      id: this.article.id,
      articleTitle: this.article.articleTitle,
      articleDesc: this.article.articleDesc,
      articleType: this.article.articleType,
      articleText: this.article.articleText,
      articleImage: this.article.articleImage
    });

    //Object.assign(this.articleForm.value, this.article);
    this.mode = State.edit;
  }

  save(): void {
    Object.assign(this.article, this.articleForm.value);

    if (this.article.id != null){
      this.articlesService.updateArticle(this.article)
        .subscribe( article => {
          this.mode = State.view;
       });
    } else {
      this.articlesService.saveNewArticle(this.article)
        .subscribe( article => {
          this.article = article;
          //this.router.navigate(['/article',{id:article.id}]);
          this.router.navigate([`/article/${article.id}`]);
          this.mode = State.view;
       });

    }
  }

  cancel(): void{ if (this.mode === State.edit) this.mode = State.view; }

}
