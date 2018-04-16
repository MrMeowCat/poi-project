import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {User} from "../../models/user";
import {AuthService} from "../../services/auth.service";

declare const $: any;

@Component({
  selector: 'poi-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  authContainerVisible: boolean = false;
  user: User;

  constructor(private userService: UserService,
              private authService: AuthService) {
  }

  ngOnInit() {
    this.userService.getCurrentUser().subscribe(user => {
      this.user = user;
    }, err => {
      console.log("Pre-auth failed");
    });
  }

  handleSuccessLogin($event) {
    this.userService.getCurrentUser().subscribe(user => {
      this.authContainerVisible = false;
      this.user = user;
    }, err => {
      console.log(err);
    })
  }

  logout() {
    this.authService.logout()
      .then(res => {
        this.user = undefined;
      })
      .catch(err => {
        console.log("failed to sign out");
      });
  }

  showAuthContainer() {
    this.authContainerVisible = true;
  }

  hideAuthContainer($event) {
    if ($($event.target).hasClass('auth-wrapper')) {
      this.authContainerVisible = false;
    }
  }

}
