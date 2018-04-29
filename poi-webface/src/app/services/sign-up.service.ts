import { Injectable } from '@angular/core';
import { HttpService } from "./http.service";
import { SignUpRequest } from "../models/sign-up-request";
import { Urls } from "../utils/urls";

@Injectable()
export class SignUpService {

  constructor(private http: HttpService) {
  }

  signUp(signUpRequest: SignUpRequest): Promise<any> {
    return this.http.post(Urls.SIGN_UP_URL, signUpRequest).toPromise();
  }
}
