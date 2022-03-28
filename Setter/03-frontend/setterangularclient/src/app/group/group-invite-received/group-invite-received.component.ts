import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {UserService} from "../../service/user.service";
import {GroupService} from "../../service/group.service";
import {GroupInviteService} from "../../service/group-invite.service";
import {NotificationService} from "../../service/notification.service";
import {GroupInvite} from "../../models/group-invite";
import {User} from "../../models/user";

@Component({
  selector: 'app-group-invite-received',
  templateUrl: './group-invite-received.component.html',
  styleUrls: ['./group-invite-received.component.css']
})
export class GroupInviteReceivedComponent implements OnInit {

  user: User = new User();
  isUserLoaded = false;

  userGroupInviteReceived: GroupInvite[];
  isUserGroupInviteReceivedLoaded = false;

  @Output()
  enterInGroupRequest = new EventEmitter<any>();

  constructor(private userService: UserService,
              private groupService: GroupService,
              private groupInviteService: GroupInviteService,
              private notificationService: NotificationService,
        ) { }

  ngOnInit(): void {
    this.handleUserDetails();
  }

  private handleUserDetails() {
    this.userService.getCurrentUser()
      .subscribe(data => {
        this.user = data;
        this.isUserLoaded = true;

        this.groupInviteService.getUserFriendInviteReceivedForUser(this.user.id).subscribe(
          data => {
            this.userGroupInviteReceived = data;
            this.isUserGroupInviteReceivedLoaded = true;
          }
        )
      })
  }

  acceptUserGroupInvitation(userGroupId: number, otherUserId: number, i: number) {
    this.groupInviteService.acceptUserGroupInvitation(userGroupId, otherUserId).subscribe(data => {
      this.notificationService.showSnackBar('Successfully enter to the group!');
      this.userGroupInviteReceived.splice(i, 1);
      data.isUserAdmin = false;
      this.enterInGroupRequest.emit(data);
    }, error => {
      this.notificationService.showSnackBar('You cannot enter to the group, some error occurred!');
    })
  }

  denyUserGroupInvitation(userGroupId: number, otherUserId: number, i: number) {
    this.groupInviteService.denyUserGroupInvitation(userGroupId, otherUserId).subscribe(data => {
      this.notificationService.showSnackBar(data.message);
      this.userGroupInviteReceived.splice(i, 1);
    }, error => {
      this.notificationService.showSnackBar(error);
    })
  }
}
