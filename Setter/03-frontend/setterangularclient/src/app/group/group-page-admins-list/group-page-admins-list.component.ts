import {Component, Inject, OnInit} from '@angular/core';
import {Group} from "../../models/group";
import {GroupPairForUserGroup} from "../../models/group-pair-for-user-group";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {NotificationService} from "../../service/notification.service";
import {FriendService} from "../../service/friend.service";
import {GroupInviteService} from "../../service/group-invite.service";

@Component({
  selector: 'app-group-page-admins-list',
  templateUrl: './group-page-admins-list.component.html',
  styleUrls: ['./group-page-admins-list.component.css']
})
export class GroupPageAdminsListComponent implements OnInit {

  group: Group = new Group();
  userPairAdmins: GroupPairForUserGroup[];
  heading: any;

  constructor(@Inject(MAT_DIALOG_DATA) public data,
              private notificationService: NotificationService,
              private groupInviteService: GroupInviteService
  ) {
    this.group = data.group;
    this.userPairAdmins = data.userPairAdmins;
    this.heading = data.heading;
  }

  ngOnInit(): void {
  }

}
