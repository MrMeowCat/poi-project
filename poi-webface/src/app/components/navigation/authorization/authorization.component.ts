import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Errors} from "../../../util/errors";
import {SignUpService} from "../../../services/sign-up.service";
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'poi-authorization',
  templateUrl: './authorization.component.html',
  styleUrls: ['./authorization.component.css']
})
export class AuthorizationComponent implements OnInit {

  signInMode: boolean = true;
  loginSuccess = true;
  usernameValid = true;
  emailValid = true;
  passwordValid = true;
  confirmValid = true;

  @Output() successLogin = new EventEmitter();
  @Output() successSignUp = new EventEmitter();

  constructor(private authService: AuthService,
              private signUpService: SignUpService) { }

  ngOnInit() {
  }

  onLogin($event) {
    this.loginSuccess = true;

    this.authService.login($event.loginRequest, $event.rememberMeParam)
      .then(res => {
        this.successLogin.emit();
      })
      .catch(err => {
        this.loginSuccess = false;
      });
  }

  onSignUp($event) {
    this.usernameValid = true;
    this.emailValid = true;
    this.passwordValid = true;
    this.confirmValid = true;

    this.signUpService.signUp($event)
      .then(res => {
        console.log("success sign up");
        this.successSignUp.emit();
      })
      .catch(err => {
        if (err.error) {
          this.handleSignUpErrors(err.error.errors);
        }
      });
  }

  private handleSignUpErrors(errors: Array<string>) {
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
