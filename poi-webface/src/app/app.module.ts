import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from "@angular/router";


import { AppComponent } from './app.component';
import { HttpClient, HttpClientModule } from "@angular/common/http";
import { ServicesModule } from "./shared/services/services.module";
import { TranslateLoader, TranslateModule } from "@ngx-translate/core";
import { TranslateHttpLoader } from "@ngx-translate/http-loader";
import { StoreModule } from "@ngrx/store";
import { reducerMap } from "./store/reducers";
import { NavigationModule } from "./navigation/navigation.module";

const routes: Routes = [
  { path: '', redirectTo: 'map', pathMatch: 'full' },
  { path: 'home', loadChildren: 'app/home/home.module#HomeModule' },
  { path: 'map', loadChildren: 'app/map/map.module#MapModule' },
];

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
    StoreModule.forRoot(reducerMap),
    ServicesModule,
    NavigationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
