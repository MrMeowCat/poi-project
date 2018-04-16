import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HttpService} from "./http.service";
import {AuthService} from "./auth.service";
import {SignUpService} from "./sign-up.service";
import {UserService} from "./user.service";

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [],
  providers: [
    HttpService,
    AuthService,
    SignUpService,
    UserService
  ]
})
export class ServicesModule { }
