import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { RouterModule, Routes } from "@angular/router";


import { AppComponent } from './app.component';
import { LoginComponent } from "./components/navigation/authorization/login/login.component";
import { HttpClientModule } from "@angular/common/http";
import { MapComponent } from './components/map/map.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { SignupComponent } from './components/navigation/authorization/signup/signup.component';
import { AuthorizationComponent } from './components/navigation/authorization/authorization.component';

const routes = [

];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MapComponent,
    NavigationComponent,
    SignupComponent,
    AuthorizationComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
