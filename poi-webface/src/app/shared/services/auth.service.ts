import { Injectable } from '@angular/core';
import { HttpService, RequestOptions } from "./http.service";
import { LoginRequest } from "../models/login-request";
import { Urls } from "../utils/urls";
import { CookieService } from "ngx-cookie-service";
import { Observable } from "rxjs/Observable";
import { User } from "../models/user";
import "rxjs/add/operator/map";

@Injectable()
export class AuthService {

  private static CSRF_TOKEN_COOKIE = 'csrftoken';
  private static CSRF_TOKEN_HEADER = 'X-CSRF-TOKEN';

  constructor(private http: HttpService,
              private cookies: CookieService) {
  }

  login(loginRequest: LoginRequest, rememberMeParam: any = { rememberMe: false }): Observable<User> {
    const options = this.http.getDefaultOptions();
    options.params = rememberMeParam;
    options.withCredentials = true;
    return this.http.post<User>(Urls.LOGIN_URL, loginRequest, options).map(user => {
      return user;
    });
  }

  logout(): Observable<any> {
    const options = this.http.getDefaultOptions();
    options.withCredentials = true;
    this.setCsrfHeader(options);
    return this.http.post(Urls.LOGOUT_URL, null, options).map(r => {
      return undefined;
    });
  }

  setCsrfHeader(options: RequestOptions) {
    let csrfToken = this.cookies.get(AuthService.CSRF_TOKEN_COOKIE);
    if (csrfToken) {
      options.headers = options.headers.append(AuthService.CSRF_TOKEN_HEADER, csrfToken);
    }
  }
}
