import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { SignUpRequest } from "../../../shared/models/sign-up-request";

@Component({
  selector: 'poi-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css', '../login/login.component.css']
})
export class SignupComponent implements OnInit {

  signUpRequest = new SignUpRequest();

  @Input() usernameValid;
  @Input() emailValid;
  @Input() passwordValid;
  @Input() confirmValid;
  @Output() signUp = new EventEmitter<SignUpRequest>();

  constructor() {
  }

  ngOnInit() {
  }

  doSignUp() {
    this.signUp.emit(this.signUpRequest);
  }
}
