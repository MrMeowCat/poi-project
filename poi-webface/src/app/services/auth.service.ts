import {Injectable} from '@angular/core';
import {HttpService, RequestOptions} from "./http.service";
import {LoginRequest} from "../models/login-request";
import {Urls} from "../util/urls";

@Injectable()
export class AuthService {

  private static JWT_TOKEN_KEY = "jwtToken";
  private jwtToken: string;

  constructor(private http: HttpService) {
  }

  login(loginRequest: LoginRequest, rememberMeParam: any = {rememberMe: false}): Promise<any> {
    const options = this.http.getDefaultOptions();
    options.params = rememberMeParam;
    options.withCredentials = true;
    return this.http.post(Urls.LOGIN_URL, loginRequest, options).toPromise()
      .then((jwt: any) => {
        this.jwtToken = jwt.jwt;

        if (!rememberMeParam.rememberMe) {
          sessionStorage.setItem(AuthService.JWT_TOKEN_KEY, jwt.jwt);
        }

        return jwt;
      });
  }

  logout(): Promise<any> {
    const options = this.http.getDefaultOptions();
    this.setAuthorizationHeader(options);
    options.withCredentials = true;
    return this.http.post(Urls.LOGOUT_URL, null, options).toPromise()
      .then(res => {
        this.jwtToken = undefined;
        sessionStorage.removeItem(AuthService.JWT_TOKEN_KEY);
        return res;
      });
  }

  setAuthorizationHeader(options: RequestOptions) {
    let token = this.jwtToken || sessionStorage.getItem(AuthService.JWT_TOKEN_KEY);
    if (!token) return;
    options.headers = options.headers.append('Authorization', `Bearer ${token}`);
  }
}
