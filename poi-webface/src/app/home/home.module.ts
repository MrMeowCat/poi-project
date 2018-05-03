import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { HomeComponent } from "./home.component";
import { homeRouter } from "./home.router";
import { RouterModule } from "@angular/router";

@NgModule({
  imports: [
    CommonModule,
    RouterModule,

    homeRouter
  ],
  declarations: [
    HomeComponent
  ]
})
export class HomeModule {
}
