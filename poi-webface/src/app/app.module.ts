import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from "@angular/router";


import { AppComponent } from './app.component';
import { LoginComponent } from "./components/navigation/authorization/login/login.component";
import { HttpClient, HttpClientModule } from "@angular/common/http";
import { MapComponent } from './components/map/map.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { SignupComponent } from './components/navigation/authorization/signup/signup.component';
import { AuthorizationComponent } from './components/navigation/authorization/authorization.component';
import { ServicesModule } from "./services/services.module";
import { HomeComponent } from './components/home/home.component';
import { TranslateLoader, TranslateModule } from "@ngx-translate/core";
import { TranslateHttpLoader } from "@ngx-translate/http-loader";
import { TopbarComponent } from './components/navigation/topbar/topbar.component';
import { StoreModule } from "@ngrx/store";
import { reducerMap } from "./store/reducers";

const routes: Routes = [
  { path: '', redirectTo: 'map', pathMatch: 'full' },
  { path: 'map', component: MapComponent },
];

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MapComponent,
    NavigationComponent,
    SignupComponent,
    AuthorizationComponent,
    HomeComponent,
    TopbarComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes),
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
    ServicesModule,
    StoreModule.forRoot(reducerMap)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
