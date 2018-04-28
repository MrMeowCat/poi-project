import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {SignUpRequest} from "../../../../models/sign-up-request";

@Component({
  selector: 'poi-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css', '../login/login.component.css']
})
export class SignupComponent implements OnInit {

  @Input() usernameValid;
  @Input() emailValid;
  @Input() passwordValid;
  @Input() confirmValid;
  @Output() signUp = new EventEmitter<SignUpRequest>();

  constructor(public signUpRequest: SignUpRequest) {
  }

  ngOnInit() {
  }

  doSignUp() {
    this.signUp.emit(this.signUpRequest);
  }
}
