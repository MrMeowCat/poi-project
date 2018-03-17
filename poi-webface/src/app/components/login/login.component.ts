import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'poi-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  static LOGIN_URL = "http://localhost:8080/api/v1/login";
  loginForm: FormGroup;

  constructor(private http: HttpClient,
              private fb: FormBuilder) { }

  ngOnInit() {
    this.loginForm = this.fb.group({
      username: [''],
      password: ['']
    });
  }

  login(body) {
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
