import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpService } from "./http.service";
import { AuthService } from "./auth.service";
import { SignUpService } from "./sign-up.service";
import { UserService } from "./user.service";
import { CookieService } from "ngx-cookie-service";

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [],
  providers: [
    HttpService,
    CookieService,
    AuthService,
    SignUpService,
    UserService,
  ]
})
export class ServicesModule {
}
