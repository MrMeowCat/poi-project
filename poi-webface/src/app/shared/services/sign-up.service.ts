import { Injectable } from '@angular/core';
import { HttpService } from "./http.service";
import { SignUpRequest } from "../models/sign-up-request";
import { ApiUrls } from "../utils/urls";
import { Observable } from "rxjs/Observable";

@Injectable()
export class SignUpService {

  constructor(private http: HttpService) {
  }

  signUp(signUpRequest: SignUpRequest): Observable<any> {
    return this.http.post(ApiUrls.SIGN_UP_URL, signUpRequest);
  }
}
