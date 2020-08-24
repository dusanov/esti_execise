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

  @Input() result$: Observable<Article[]>;

  constructor(private articlesService: ArticlesService) {
    this.result$ = articlesService.resolveItems();
   }

  ngOnInit(): void {}

}
