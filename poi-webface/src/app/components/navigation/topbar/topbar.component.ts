import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { User } from "../../../models/user";
import { Constants } from "../../../util/constants";
import { TranslateService } from "@ngx-translate/core";

@Component({
  selector: 'poi-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.css']
})
export class TopbarComponent implements OnInit {

  userPopupVisible = false;
  langPopupVisible = false;
  locales = Constants.LOCALES;
  languages = Constants.LANGUAGES;

  @Input() user: User;
  @Output() onLoginClick = new EventEmitter();
  @Output() onLogoutClick = new EventEmitter();
  @Output() onChangeLocale = new EventEmitter<string>();

  constructor(private tranlateService: TranslateService) { }

  ngOnInit() {
  }

  loginClick() {
    this.onLoginClick.emit();
  }

  logoutClick() {
    this.onLogoutClick.emit();
  }

  changeLocale(locale: string, $event) {
    this.onChangeLocale.emit(locale);
  }

  currentLanguage() : string {
    return this.tranlateService.currentLang;
  }

  toggleUserPopup() {
    this.userPopupVisible = !this.userPopupVisible;
  }

  toggleLangPopup() {
    this.langPopupVisible = !this.langPopupVisible;
  }

  hidePopups($event) {
    this.userPopupVisible = false;
    this.langPopupVisible = false;
  }
}
