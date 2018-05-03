import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { TranslateService } from "@ngx-translate/core";
import { Store } from "@ngrx/store";
import { ThemeChangeAction } from "../../store/actions";
import { State } from "../../store/states";
import { StyledTheme, StyledThemeArray } from "../../shared/models/styled-themes";
import { Constants } from "../../shared/utils/constants";
import { User } from "../../shared/models/user";
import { UserService } from "../../shared/services/user.service";

declare var $:any;

@Component({
  selector: 'poi-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.css']
})
export class TopbarComponent implements OnInit {

  themeArray = StyledThemeArray;
  selectedTheme: StyledTheme;
  userPopupVisible = false;
  langPopupVisible = false;
  themePopupVisible = false;
  locales = Constants.LOCALES;
  languages = Constants.LANGUAGES;
  file: File;

  @Input() user: User;
  @Output() loginClick = new EventEmitter();
  @Output() logoutClick = new EventEmitter();
  @Output() localeChange = new EventEmitter<string>();
  @Output() avatarChange = new EventEmitter<any>();

  constructor(private translateService: TranslateService,
              private userService: UserService,
              private store: Store<State>) {
    this.store.select(state => state.theme).subscribe(theme => {
      this.selectedTheme = theme;
    });
  }

  ngOnInit() {
  }

  emitLoginClick() {
    this.loginClick.emit();
  }

  emitLogoutClick() {
    this.logoutClick.emit();
  }

  emitLocaleChange(locale: string, $event) {
    this.localeChange.emit(locale);
  }

  currentLanguage() : string {
    return this.translateService.currentLang;
  }

  toggleUserPopup() {
    this.userPopupVisible = !this.userPopupVisible;
  }

  toggleLangPopup() {
    this.langPopupVisible = !this.langPopupVisible;
  }

  toggleThemePopup() {
    this.themePopupVisible = !this.themePopupVisible;
  }

  hidePopups($event) {
    this.userPopupVisible = false;
    this.langPopupVisible = false;
    this.themePopupVisible = false;
  }

  openAvatarFileDialog() {
    const avatar = $('#av');
    avatar.on('change', e => {
      const file = e.target.files[0];
      this.userService.setAvatar(file).subscribe(res => {
        console.log(res);
        this.avatarChange.emit(res);
      }, err => {
        console.log(err);
      });
    });
    avatar.click();
  }

  changeTheme(theme: StyledTheme) {
    this.store.dispatch(new ThemeChangeAction(theme));
  }
}
