import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { AuthorizationComponent } from "./authorization/authorization.component";
import { LoginComponent } from "./authorization/login/login.component";
import { SignupComponent } from "./authorization/signup/signup.component";
import { TopbarComponent } from "./topbar/topbar.component";
import { TranslateModule } from "@ngx-translate/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { NavigationComponent } from "./navigation.component";

@NgModule({
  imports: [
    CommonModule,
    TranslateModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  exports: [
    NavigationComponent
  ],
  declarations: [
    AuthorizationComponent,
    LoginComponent,
    SignupComponent,
    TopbarComponent,
    NavigationComponent
  ]
})
export class NavigationModule {
}
