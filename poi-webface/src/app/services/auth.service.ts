import { Injectable } from '@angular/core';
import { HttpService, RequestOptions } from "./http.service";
import { LoginRequest } from "../models/login-request";
import { Urls } from "../util/urls";
import { CookieService } from "ngx-cookie-service";

@Injectable()
export class AuthService {

  private static CSRF_TOKEN_COOKIE = 'csrftoken';
  private static CSRF_TOKEN_HEADER = 'X-CSRF-TOKEN';

  constructor(private http: HttpService,
              private cookies: CookieService) {
  }

  login(loginRequest: LoginRequest, rememberMeParam: any = { rememberMe: false }): Promise<any> {
    const options = this.http.getDefaultOptions();
    options.params = rememberMeParam;
    options.withCredentials = true;
    return this.http.post(Urls.LOGIN_URL, loginRequest, options).toPromise();
  }

  logout(): Promise<any> {
    const options = this.http.getDefaultOptions();
    options.withCredentials = true;
    this.setCsrfHeader(options);
    return this.http.post(Urls.LOGOUT_URL, null, options).toPromise();
  }

  setCsrfHeader(options: RequestOptions) {
    let csrfToken = this.cookies.get(AuthService.CSRF_TOKEN_COOKIE);
    if (csrfToken) {
      options.headers = options.headers.append(AuthService.CSRF_TOKEN_HEADER, csrfToken);
    }
  }
}
