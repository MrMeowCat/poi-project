import {Component, OnInit} from '@angular/core';
import {LoginRequest} from "../../../../models/login-request";
import {LoginService} from "../../../../services/login.service";
import {FormControl} from "@angular/forms";

declare var $: any;

@Component({
  selector: 'poi-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  success = true;

  rememberMeParam = {
    rememberMe: false
  };

  constructor(private loginService: LoginService,
              public loginRequest: LoginRequest) {
  }

  ngOnInit() {
  }

  login() {
    this.success = true;

    this.loginService.login(this.loginRequest, this.rememberMeParam)
      .then(res => {
        //todo close wrapper
      })
      .catch(err => {
        this.success = false;
      });
  }
}
