import { Component, OnInit } from '@angular/core';
import { UserService } from "../../services/user.service";
import { User } from "../../models/user";
import { AuthService } from "../../services/auth.service";
import { Constants } from "../../util/constants";
import { TranslateService } from "@ngx-translate/core";
import { CookieService } from "ngx-cookie-service";

declare const $: any;

@Component({
  selector: 'poi-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  authContainerVisible: boolean = false;
  user: User;

  constructor(private userService: UserService,
              private authService: AuthService,
              private translate: TranslateService,
              private cookies: CookieService) {
  }

  ngOnInit() {
    this.userService.getCurrentUser().subscribe(user => {
      this.user = user;
      this.setLanguage(user.language);
    }, err => {
      console.log("Pre-auth failed");
    });
  }

  handleSuccessLogin($event) {
    this.userService.getCurrentUser().subscribe(user => {
      this.authContainerVisible = false;
      this.user = user;
      this.setLanguage(user.language);
    }, err => {
      console.log(err);
    })
  }

  handleLogout($event) {
    this.authService.logout()
      .then(res => {
        this.user = undefined;
      })
      .catch(err => {
        console.log("failed to sign out");
      });
  }

  showAuthContainer($event) {
    this.authContainerVisible = true;
  }

  hideAuthContainer($event) {
    if ($($event.target).hasClass('auth-wrapper')) {
      this.authContainerVisible = false;
    }
  }

  changeLocale(locale: string) {
    if (this.translate.currentLang == locale) return;

    this.userService.setLocale(locale).subscribe(res => {
      this.setLanguage(locale);
    });
  }

  setLanguage(language) {
    this.cookies.set('locale', language, 365);
    if (this.translate.currentLang != language) {
      this.translate.use(language);
    }
  }
}
