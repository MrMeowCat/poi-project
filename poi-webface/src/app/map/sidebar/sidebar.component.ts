import { Component, OnInit } from '@angular/core';
import { State } from "../../store/states";
import { Store } from "@ngrx/store";
import { Observable } from "rxjs/Observable";
import "rxjs/add/observable/of";
import { User } from "../../shared/models/user";
import { Theme } from "../../shared/models/theme";
import { AuthShowAction, SidebarTabChangeAction, ThemeChangeAction, ThemesLoadAction } from "../../store/actions";
import { Constants } from "../../shared/utils/constants";
import { ThemeService } from "../../shared/services/theme.service";
import { CookieService } from "ngx-cookie-service";
import { ThemeImportValidationState } from "./sidebar-themes/validation";
import { DefaultThemes } from "../../shared/models/default-themes";
import { TranslateService } from "@ngx-translate/core";

@Component({
  selector: 'poi-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  isVisible = false;
  sidebarTab;
  themeTab = 0;
  themeImportVisible = false;
  user: User;
  selectedTheme: Theme;
  customThemes: Theme[];
  themeImportValidationState = new ThemeImportValidationState();

  user$: Observable<User>;
  sidebarVisible$: Observable<boolean>;
  sidebarTab$: Observable<number>;
  theme$: Observable<Theme>;
  themes$: Observable<Theme[]>;

  constructor(private store: Store<State>,
              private themeService: ThemeService,
              private cookies: CookieService) {
    this.sidebarVisible$ = store.select(state => state.sidebarVisible);
    this.sidebarTab$ = store.select(state => state.sidebarTab);
    this.user$ = store.select(state => state.user);
    this.theme$ = store.select(state => state.theme);
    this.themes$ = store.select(state => state.themes);

    this.sidebarVisible$.subscribe(isVisible => this.isVisible = isVisible);
    this.sidebarTab$.subscribe(sidebarTab => this.sidebarTab = sidebarTab);
    this.user$.subscribe(user => {
      this.user = user;
      if (!user) {
        this.store.dispatch(new ThemeChangeAction(DefaultThemes.Standard));
      }
    });
    this.theme$.subscribe(theme => {
      this.selectedTheme = theme;
      this.themeTab = this.selectedTheme.id ? 1 : 0;
    });
    this.themes$.subscribe(themes => this.customThemes = themes);
  }

  ngOnInit() {
  }

  setSidebarTab(tab: number) {
    if (tab == this.sidebarTab) {
      return;
    }
    this.store.dispatch(new SidebarTabChangeAction(tab));
  }

  onThemeChange(theme: Theme) {
    this.store.dispatch(new ThemeChangeAction(theme));
    if (theme.custom) {
      this.themeService.updateTheme(theme, true).subscribe();
    } else {
      if (this.user) {
        this.themeService.updateTheme(null, true).subscribe();
      }
      this.cookies.set(Constants.COOKIE_THEME, theme.name, 365);
    }
  }

  onThemeDelete(theme: Theme) {
    //todo create action
    this.customThemes = this.customThemes.filter(th => th.name != theme.name);
    if (theme.name == this.selectedTheme.name) {
      this.store.dispatch(new ThemeChangeAction(DefaultThemes.Standard));
    }
    this.store.dispatch(new ThemesLoadAction(this.customThemes));
    this.themeService.deleteTheme(theme.id).subscribe();
  }

  onThemeImportSave($event: {themeName: string, themeStyle: string}) {
    console.log($event);
    const nameValid = $event.themeName.length > 0;
    const styleValid = $event.themeStyle.length > 0;
    if (nameValid && styleValid) {
      const theme = new Theme();
      theme.name = $event.themeName;
      theme.style = $event.themeStyle;
      this.themeService.createTheme(theme).subscribe(theme => {
        this.customThemes.push(theme); //todo create action
        this.store.dispatch(new ThemesLoadAction(this.customThemes));
        this.themeImportVisible = false;
      });
    }
    this.themeImportValidationState.nameValid = nameValid;
    this.themeImportValidationState.styleValid = styleValid;
  }

  onThemeImportOpen($event) {
    this.themeImportVisible = true;
  }

  onThemeImportClose($event) {
    this.themeImportVisible = false;
  }

  onAuthShow($event) {
    this.store.dispatch(new AuthShowAction());
  }
}
