import { Component, OnInit } from '@angular/core';
import {User} from "../../models/user";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  user: User = new User();
  isUserDataLoaded = false;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.handleUserDetails();
  }

  private handleUserDetails() {
    /*this.userService.getCurrentUser()
      .subscribe(data => {
        this.user = data;
        this.isUserDataLoaded = true;
      });*/
  }

}
