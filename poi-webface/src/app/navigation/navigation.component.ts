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
import { DefaultThemeArray, DefaultThemes } from "../shared/models/default-themes";
import { Theme } from "../shared/models/theme";
import { Constants } from "../shared/utils/constants";

declare const $: any;

@Component({
  selector: 'poi-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  authContainerVisible: boolean = false;
  user: User;
  selectedTheme: Theme = DefaultThemes.Standard;
  customThemes: Theme[] = [];

  constructor(private userService: UserService,
              private themeService: ThemeService,
              private authService: AuthService,
              private translate: TranslateService,
              private cookies: CookieService,
              private store: Store<State>) {
    this.store.select(state => state.theme).subscribe(theme => {
      this.selectedTheme = theme;
    });
  }

  ngOnInit() {
    this.authService.isAuthenticated().subscribe(yes => {

      this.userService.getCurrentUser().subscribe(user => {
        this.user = user;
        this.setLanguage(user.language);
      });

      this.themeService.getCurrentTheme().subscribe(
        theme => this.successLoadThemeCb(theme),
        err => this.failLoadThemeCb()
      );

      this.themeService.getThemes().subscribe(themes => {
        this.customThemes = themes;
      });
    }, no => {
      console.log("Pre-auth failed");
      this.store.dispatch(new ThemeChangeAction(this.getThemeFromCookie()));
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
    });

    this.themeService.getCurrentTheme().subscribe(
      theme => this.successLoadThemeCb(theme),
      err => this.failLoadThemeCb()
    );

    this.themeService.getThemes().subscribe(themes => {
      this.customThemes = themes;
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

  onThemeChange(theme: Theme) {
    this.store.dispatch(new ThemeChangeAction(theme));
    if (theme.custom) {
      this.themeService.updateTheme(theme, true).subscribe();
    } else {
      this.themeService.updateTheme(null, true).subscribe();
      this.cookies.set(Constants.COOKIE_THEME, theme.name, 365);
    }
  }

  setLanguage(language) {
    this.cookies.set(Constants.COOKIE_LOCALE, language, 365);
    if (this.translate.currentLang != language) {
      this.translate.use(language);
    }
  }

  hideAuthContainer($event) {
    if ($($event.target).hasClass('auth-wrapper')) {
      this.authContainerVisible = false;
    }
  }

  private successLoadThemeCb(theme: Theme) {
    this.cookies.delete(Constants.COOKIE_THEME);
    this.store.dispatch(new ThemeChangeAction(theme));
  }

  private failLoadThemeCb() {
    console.log("No selected themes. Apply default theme");
    this.store.dispatch(new ThemeChangeAction(this.getThemeFromCookie()));
  }

  private getThemeFromCookie(): Theme {
    const themeName = this.cookies.get(Constants.COOKIE_THEME);
    return DefaultThemeArray.find(theme => theme.name == themeName)
      || DefaultThemes.Standard;
  }
}
