import { Component, OnInit } from '@angular/core';
import { TranslateService } from "@ngx-translate/core";
import { CookieService } from "ngx-cookie-service";
import { Store } from "@ngrx/store";
import { ThemeChangeAction } from "../store/actions";
import { State } from "../store/states";
import { User } from "../shared/models/user";
import { UserService } from "../shared/services/user.service";
import { ThemeService } from "../shared/services/theme.service";
import { AuthService } from "../shared/services/auth.service";
import { StyledThemes } from "../shared/models/styled-themes";

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
              private themeService: ThemeService,
              private authService: AuthService,
              private translate: TranslateService,
              private cookies: CookieService,
              private store: Store<State>) {
  }

  ngOnInit() {
    this.userService.getCurrentUser().subscribe(user => {
      this.user = user;
      this.setLanguage(user.language);
    }, err => {
      console.log("Pre-auth failed");
    });

    this.themeService.getCurrentTheme().subscribe(theme => {
      let styledTheme;
      try {
        styledTheme = this.themeService.convertTheme(theme);
      } catch (e) {
        console.log("Failed to convert theme. Using defualt theme.");
        styledTheme = StyledThemes.Standard;
      }
      this.store.dispatch(new ThemeChangeAction(styledTheme));
    }, err => {
      console.log("Theme pre-load failed");
    });
  }

  onLoginClick($event) {
    this.authContainerVisible = true;
  }

  onSuccessLogin($event) {
    this.userService.getCurrentUser().subscribe(user => {
      this.authContainerVisible = false;
      this.user = user;
      this.setLanguage(user.language);
    }, err => {
      console.log(err);
    });

    this.themeService.getCurrentTheme().subscribe(theme => {
      let styledTheme;
      try {
        styledTheme = this.themeService.convertTheme(theme);
      } catch ( e ) {
        console.log("Failed to convert theme. Using defualt theme.");
        styledTheme = StyledThemes.Standard;
      }
      this.store.dispatch(new ThemeChangeAction(styledTheme));
    }, err => {
      console.log(err);
    });
  }

  onLogout($event) {
    this.authService.logout().subscribe(res => {
      this.user = undefined;
    }, err => {
      console.log("failed to sign out");
    });
  }

  onLocaleChange(locale: string) {
    if (this.translate.currentLang == locale) return;

    this.userService.setLocale(locale).subscribe(res => {
      this.setLanguage(locale);
    });
  }

  onAvatarChange(avatar) {
    if (!avatar) {
      return;
    }

    this.user.avatarFull = avatar.avatarFull;
    this.user.avatarThumbnail = avatar.avatarThumbnail;
    this.user.avatarIcon = avatar.avatarIcon;
  }

  setLanguage(language) {
    this.cookies.set('locale', language, 365);
    if (this.translate.currentLang != language) {
      this.translate.use(language);
    }
  }

  hideAuthContainer($event) {
    if ($($event.target).hasClass('auth-wrapper')) {
      this.authContainerVisible = false;
    }
  }
}
