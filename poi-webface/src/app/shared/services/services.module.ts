import { NgModule } from '@angular/core';
import { HttpService } from "./http.service";
import { AuthService } from "./auth.service";
import { SignUpService } from "./sign-up.service";
import { UserService } from "./user.service";
import { CookieService } from "ngx-cookie-service";
import { ThemeService } from "./theme.service";

@NgModule({
  imports: [
  ],
  exports: [
  ],
  declarations: [],
  providers: [
    HttpService,
    CookieService,
    AuthService,
    SignUpService,
    UserService,
    ThemeService,
  ]
})
export class ServicesModule {
}
