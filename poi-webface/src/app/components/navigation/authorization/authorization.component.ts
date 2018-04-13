import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'poi-authorization',
  templateUrl: './authorization.component.html',
  styleUrls: ['./authorization.component.css']
})
export class AuthorizationComponent implements OnInit {

  signInMode: boolean = true;

  constructor() { }

  ngOnInit() {
  }

}
