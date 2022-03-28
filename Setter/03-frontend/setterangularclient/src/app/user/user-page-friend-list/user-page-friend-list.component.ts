import {Component, Inject, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {NotificationService} from "../../service/notification.service";
import {FriendService} from "../../service/friend.service";
import {FriendPair} from "../../models/friend-pair";

@Component({
  selector: 'app-user-page-friend-list',
  templateUrl: './user-page-friend-list.component.html',
  styleUrls: ['./user-page-friend-list.component.css']
})
export class UserPageFriendListComponent implements OnInit {

  userPage: User = new User();
  userFriendPairs: FriendPair[];
  heading: any;

  constructor(@Inject(MAT_DIALOG_DATA) public data,
              private notificationService: NotificationService,
              private friendService: FriendService
  ) {
    this.userPage = data.userPage;
    this.userFriendPairs = data.userFriendPairs;
    this.heading = data.heading;
  }

  ngOnInit(): void {
  }
}
