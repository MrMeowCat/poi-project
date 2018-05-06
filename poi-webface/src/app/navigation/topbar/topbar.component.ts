import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { TranslateService } from "@ngx-translate/core";
import { DefaultThemeArray } from "../../shared/models/default-themes";
import { Constants } from "../../shared/utils/constants";
import { User } from "../../shared/models/user";
import { UserService } from "../../shared/services/user.service";
import { Theme } from "../../shared/models/theme";

declare var $:any;

@Component({
  selector: 'poi-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.css']
})
export class TopbarComponent implements OnInit {

  themeArray = DefaultThemeArray;
  userPopupVisible = false;
  langPopupVisible = false;
  themePopupVisible = false;
  locales = Constants.LOCALES;
  languages = Constants.LANGUAGES;
  file: File;

  @Input() user: User;
  @Input() selectedTheme: Theme;
  @Input() customThemes: Theme[];
  @Output() hamClick = new EventEmitter();
  @Output() loginClick = new EventEmitter();
  @Output() logoutClick = new EventEmitter();
  @Output() localeChange = new EventEmitter<string>();
  @Output() avatarChange = new EventEmitter<any>();
  @Output() themeChange = new EventEmitter<Theme>();

  constructor(private translateService: TranslateService,
              private userService: UserService) {
  }

  ngOnInit() {
  }

  emitHamClick() {
    this.hamClick.emit();
  }

  emitLoginClick() {
    this.loginClick.emit();
  }

  emitLogoutClick() {
    this.hidePopups(null);
    this.logoutClick.emit();
  }

  emitLocaleChange(locale: string, $event) {
    this.hidePopups(null);
    this.localeChange.emit(locale);
  }

  changeTheme(theme: Theme) {
    if (theme == this.selectedTheme) return;
    this.themeChange.emit(theme);
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
}
