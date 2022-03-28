import {Component, Inject, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {NotificationService} from "../../service/notification.service";
import {GroupInviteService} from "../../service/group-invite.service";
import {GroupPair} from "../../models/group-pair";

@Component({
  selector: 'app-user-page-group-list',
  templateUrl: './user-page-group-list.component.html',
  styleUrls: ['./user-page-group-list.component.css']
})
export class UserPageGroupListComponent implements OnInit {

  userPage: User = new User();
  userGroupPairs: GroupPair[];
  heading: any;

  constructor(@Inject(MAT_DIALOG_DATA) public data,
              private notificationService: NotificationService,
              private groupInviteService: GroupInviteService
  ) {
    this.userPage = data.userPage;
    this.userGroupPairs = data.userGroupPairs;
    this.heading = data.heading;
  }

  ngOnInit(): void {
  }
}
