import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ArticlesComponent } from './articles/articles.component';
import { ArticleDetailComponent } from './article-detail/article-detail.component';

const routes: Routes = [
  {path: 'articles', component: ArticlesComponent},
  { path: '', redirectTo: '/articles', pathMatch: 'full' },
  { path: 'article/:id', component: ArticleDetailComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
