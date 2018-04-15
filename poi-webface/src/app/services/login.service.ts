import { Injectable } from '@angular/core';
import {HttpService} from "./http.service";
import {LoginRequest} from "../models/login-request";
import {Urls} from "../util/urls";

@Injectable()
export class LoginService {

  jwtToken: string;

  constructor(private http: HttpService) { }

  login(loginRequest: LoginRequest, rememberMe: any) : Promise<any> {
    const options = this.http.getDefaultOptions();
    options.params = rememberMe;
    options.withCredentials = true;
    return this.http.post(Urls.LOGIN_URL, loginRequest, options).toPromise().then((jwt: any) => {
      this.jwtToken = jwt.jwt;
      return jwt;
    });
  }

  logout() : Promise<any> {
    return null;
  }
}
