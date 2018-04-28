import { Injectable } from '@angular/core';
import { HttpService } from "./http.service";
import { Observable } from "rxjs/Observable";
import { Urls } from "../util/urls";
import { AuthService } from "./auth.service";
import { HttpHeaders } from "@angular/common/http";

@Injectable()
export class UserService {

  constructor(private http: HttpService,
              private authService: AuthService) {
  }

  getUser(id: string): Observable<any> {
    return this.http.get(Urls.USERS_URL + `/${id}`);
  }

  getCurrentUser(): Observable<any> {
    const options = this.http.getDefaultOptions();
    options.withCredentials = true;
    this.authService.setCsrfHeader(options);
    return this.http.get(Urls.CURRENT_USER_URL, options);
  }

  setLocale(locale: string): Observable<any> {
    const options = this.http.getDefaultOptions();
    options.params = {
      locale: locale
    };
    options.withCredentials = true;
    this.authService.setCsrfHeader(options);
    return this.http.put(Urls.SET_LOCALE_URL, null, options);
  }

  setAvatar(file): Observable<any> {
    const headers = new HttpHeaders();
    const options = this.http.getDefaultOptions();
    options.withCredentials = true;
    options.headers = headers;
    this.authService.setCsrfHeader(options);
    const fd = new FormData();
    fd.append('file', file);
    return this.http.put(Urls.SET_AVATAR_URL, fd, options);
  }
}
