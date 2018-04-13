import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Component({
  selector: 'poi-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  static LOGIN_URL = "http://localhost:8080/api/v1/login";
  username: string = "";
  password: string = "";
  rememberMe: boolean = false;

  constructor(private http: HttpClient) { }

  ngOnInit() {
  }

  login() {
    let body = {
      username: this.username,
      password: this.password,
      rememberMe: this.rememberMe
    };

    console.log(body);

    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });


    this.http.post(LoginComponent.LOGIN_URL, body, {headers}).subscribe(res => {
      console.log(res);
    }, err => {
      console.log(err);
    });
  }
}
