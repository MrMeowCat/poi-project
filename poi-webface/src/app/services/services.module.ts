import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HttpService} from "./http.service";
import {LoginService} from "./login.service";
import {SignUpService} from "./sign-up.service";

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [],
  providers: [
    HttpService,
    LoginService,
    SignUpService
  ]
})
export class ServicesModule { }
