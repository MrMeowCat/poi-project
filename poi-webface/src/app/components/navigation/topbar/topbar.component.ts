import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { User } from "../../../models/user";
import { Constants } from "../../../util/constants";
import { TranslateService } from "@ngx-translate/core";
import { HttpService } from "../../../services/http.service";
import { UserService } from "../../../services/user.service";

declare var $:any;

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
  file: File;

  @Input() user: User;
  @Output() onLoginClick = new EventEmitter();
  @Output() onLogoutClick = new EventEmitter();
  @Output() onChangeLocale = new EventEmitter<string>();
  @Output() onAvatarChanged = new EventEmitter<any>();

  constructor(private translateService: TranslateService,
              private userService: UserService) { }

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
    return this.translateService.currentLang;
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

  openAvatarFileDialog() {
    const avatar = $('#av');
    avatar.on('change', e => {
      const file = e.target.files[0];
      this.userService.setAvatar(file).subscribe(res => {
        console.log(res);
        this.onAvatarChanged.emit(res);
      }, err => {
        console.log(err);
      });
    });
    avatar.click();
  }
}
