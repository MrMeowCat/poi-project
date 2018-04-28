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
  @Output() loginClick = new EventEmitter();
  @Output() logoutClick = new EventEmitter();
  @Output() localeChange = new EventEmitter<string>();
  @Output() avatarChange = new EventEmitter<any>();

  constructor(private translateService: TranslateService,
              private userService: UserService) { }

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
        this.avatarChange.emit(res);
      }, err => {
        console.log(err);
      });
    });
    avatar.click();
  }
}
