import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {LoginRequest} from "./login-request";
import {SignUpRequest} from "./sign-up-request";

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [],
  providers: [
    LoginRequest,
    SignUpRequest
  ]
})
export class ModelsModule { }
