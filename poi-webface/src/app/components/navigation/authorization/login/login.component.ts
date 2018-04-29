import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { LoginRequest } from "../../../../models/login-request";

declare var $: any;

@Component({
  selector: 'poi-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginRequest = new LoginRequest();

  @Input() loginSuccess;
  @Output() login = new EventEmitter<any>();

  rememberMeParam = {
    rememberMe: false
  };

  constructor() {
  }

  ngOnInit() {
  }

  doLogin() {
    this.login.emit({
      loginRequest: this.loginRequest,
      rememberMeParam: this.rememberMeParam
    });
  }
}
