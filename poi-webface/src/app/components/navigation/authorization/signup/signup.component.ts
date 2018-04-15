import {Component, OnInit} from '@angular/core';
import {SignUpRequest} from "../../../../models/sign-up-request";
import {SignUpService} from "../../../../services/sign-up.service";
import {Errors} from "../../../../util/errors";

declare var $: any;

@Component({
  selector: 'poi-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css', '../login/login.component.css']
})
export class SignupComponent implements OnInit {

  usernameValid = true;
  emailValid = true;
  passwordValid = true;
  confirmValid = true;

  constructor(private signUpService: SignUpService,
              public signUpRequest: SignUpRequest) {
  }

  ngOnInit() {
  }

  signUp() {
    this.usernameValid = true;
    this.emailValid = true;
    this.passwordValid = true;
    this.confirmValid = true;

    this.signUpService.signUp(this.signUpRequest)
      .then(res => {
        console.log("success sign up");
      })
      .catch(err => {
        if (err.error) {
          this.handleFormErrors(err.error.errors);
        }
      });
  }

  private handleFormErrors(errors: Array<string>) {
    errors.forEach((error) => {
      switch (error) {
        case Errors.SIGN_UP.FORM_EMPTY:
          this.usernameValid = false;
          this.emailValid = false;
          this.passwordValid = false;
          this.confirmValid = false;
          break;
        case Errors.SIGN_UP.USERNAME_EMPTY:
          this.usernameValid = false;
          break;
        case Errors.SIGN_UP.EMAIL_EMPTY:
          this.emailValid = false;
          break;
        case Errors.SIGN_UP.PASSWORD_EMPTY:
          this.passwordValid = false;
          break;
        case Errors.SIGN_UP.CONFIRM_EMPTY:
          this.confirmValid = false;
          break;
        case Errors.SIGN_UP.USER_EXISTS:
          this.usernameValid = false;
          break;
        case Errors.SIGN_UP.EMAIL_EXISTS:
          this.emailValid = false;
          break;
        case Errors.SIGN_UP.EMAIL_INCORRECT:
          this.emailValid = false;
          break;
        case Errors.SIGN_UP.PASSWORD_INCORRECT:
          this.passwordValid = false;
          break;
        case Errors.SIGN_UP.PASSWORD_MISMATCH:
          this.passwordValid = false;
          this.confirmValid = false;
          break;
      }
    });
  }
}
