import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {LoginRequest} from "./login-request";
import {SignUpRequest} from "./sign-up-request";
import {User} from "./user";

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [],
  providers: [
    LoginRequest,
    SignUpRequest,
    User
  ]
})
export class ModelsModule { }
