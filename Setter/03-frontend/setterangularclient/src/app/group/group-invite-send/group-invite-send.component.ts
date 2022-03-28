import { Component, OnInit } from '@angular/core';
import {UserService} from "../../service/user.service";
import {GroupService} from "../../service/group.service";
import {GroupInviteService} from "../../service/group-invite.service";
import {NotificationService} from "../../service/notification.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder} from "@angular/forms";
import {ImageUploadService} from "../../service/image-upload.service";
import {GroupInvite} from "../../models/group-invite";
import {GroupPair} from "../../models/group-pair";
import {User} from "../../models/user";

@Component({
  selector: 'app-group-invite-send',
  templateUrl: './group-invite-send.component.html',
  styleUrls: ['./group-invite-send.component.css']
})
export class GroupInviteSendComponent implements OnInit {

  user: User = new User();
  isUserLoaded = false;

  userGroupInviteSend: GroupInvite[];
  isUserGroupInviteSendLoaded = false;

  constructor(private userService: UserService,
              private groupService: GroupService,
              private groupInviteService: GroupInviteService,
              private notificationService: NotificationService
  ) { }

  ngOnInit(): void {
    this.handleUserDetails();
  }

  private handleUserDetails() {
    this.userService.getCurrentUser()
      .subscribe(data => {
        this.user = data;
        this.isUserLoaded = true;

        this.groupInviteService.getUserFriendInviteSendForUser(this.user.id).subscribe(
          data => {
            this.userGroupInviteSend = data;
            this.isUserGroupInviteSendLoaded = true;
          }
        )
      })
  }

  denyUserGroupInvitation(userGroupId: number, otherUserId: number, i: number) {
    this.groupInviteService.denyUserGroupInvitation(userGroupId,otherUserId).subscribe(data => {
      this.notificationService.showSnackBar(data.message);
      this.userGroupInviteSend.splice(i, 1);
    }, error => {
      this.notificationService.showSnackBar(error);
    })
  }
}
