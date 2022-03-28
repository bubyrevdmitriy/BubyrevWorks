import {Component, Inject, OnInit} from '@angular/core';
import {Group} from "../../models/group";
import {GroupPairForUserGroup} from "../../models/group-pair-for-user-group";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {NotificationService} from "../../service/notification.service";
import {GroupInviteService} from "../../service/group-invite.service";
import {User} from "../../models/user";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'app-group-page-users-list',
  templateUrl: './group-page-users-list.component.html',
  styleUrls: ['./group-page-users-list.component.css']
})
export class GroupPageUsersListComponent implements OnInit {

  group: Group = new Group();
  userPairs: GroupPairForUserGroup[];
  heading: any;
  user: User;
  isDataLoaded = false;
  isGroupInfoChanged: boolean = false;

  constructor(public matDialogRef: MatDialogRef<GroupPageUsersListComponent>,
              @Inject(MAT_DIALOG_DATA) public data,
              private userService: UserService,
              private notificationService: NotificationService,
              private groupInviteService: GroupInviteService
  ) {
    this.group = data.group;
    this.userPairs = data.userPairs;
    this.heading = data.heading;
  }

  ngOnInit(): void {
    console.log(this.userPairs);
    this.userService.getCurrentUser()
      .subscribe(data => {
        this.user = data;
        this.isDataLoaded = true;
      })
  }

  kickUserFromGroup(userId: number) {
    //kickUserFromUserGroup
    this.groupInviteService.kickUserFromUserGroup(this.group.id, userId).subscribe(data => {
      for(let i=0; i<this.userPairs.length; i++){
        if(this.userPairs[i].user.id == userId) {
          this.userPairs.splice(i, 1);
          break;
        }
      }
      this.isGroupInfoChanged = true;
      this.notificationService.showSnackBar(data.message);
    }, error => {
      this.notificationService.showSnackBar(error);
    })
  }

  makeUserNewAdmin(userId: number) {
    this.groupInviteService.makeNewAdmin(this.group.id, userId).subscribe(data => {
      for(let i=0; i<this.userPairs.length; i++){
        if(this.userPairs[i].user.id == userId) {
          this.userPairs[i].isUserAdmin = true; //use i instead of 0
          break;
        }
      }
      this.isGroupInfoChanged = true;
      this.notificationService.showSnackBar(data.message);
    }, error => {
      this.notificationService.showSnackBar(error);
    })
  }

  closeForm() {
    this.matDialogRef.close({isGroupInfoChanged: this.isGroupInfoChanged});
  }
}
