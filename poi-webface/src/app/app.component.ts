import { Component } from '@angular/core';
import { TranslateService } from "@ngx-translate/core";
import { CookieService } from "ngx-cookie-service";

@Component({
  selector: 'poi-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private translate: TranslateService,
              private cookeis: CookieService) {
    const locale = this.cookeis.get('locale');
    translate.setDefaultLang('en');
    translate.use(locale ? locale : 'en');
  }
}
