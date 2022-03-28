import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {GroupInviteService} from "../../service/group-invite.service";
import {CommonImage} from "../../models/common-image";
import {GroupAuthor} from "../../models/group-author";
import {User} from "../../models/user";
import {NotificationService} from "../../service/notification.service";

@Component({
  selector: 'app-group-invite',
  templateUrl: './group-invite.component.html',
  styleUrls: ['./group-invite.component.css']
})
export class GroupInviteComponent implements OnInit {

  userPage: User = new User();
  groups: GroupAuthor[];
  isGroupsLoaded = false;
  heading: any;

  constructor(
              @Inject(MAT_DIALOG_DATA) public data,
              private notificationService: NotificationService,
              private groupInviteService: GroupInviteService
  ) {
    this.userPage = data.userPage;
    this.heading = data.heading;
  }

  ngOnInit(): void {
    this.handleUserDetails();
  }

  private handleUserDetails() {
    this.groupInviteService.getUserGroupAvailableToInviteForUser(this.data.userPage.id).subscribe(
      data => {
        this.groups = data;
        this.isGroupsLoaded = true;
      })
  }

  inviteUserToGroup(userGroupId: number, i: number) {
    this.groupInviteService.inviteUserToUserGroup(userGroupId,this.userPage.id).subscribe(data => {
      this.notificationService.showSnackBar(data.message);
      this.groups.splice(i, 1);
    }, error => {
      this.notificationService.showSnackBar(error);
    })
  }
}
