import { Component, OnInit } from '@angular/core';
import {User} from "../../models/user";
import {FriendInvite} from "../../models/friend-invite";
import {FriendPair} from "../../models/friend-pair";
import {UserService} from "../../service/user.service";
import {FriendService} from "../../service/friend.service";
import {NotificationService} from "../../service/notification.service";

@Component({
  selector: 'app-friend-invites-send',
  templateUrl: './friend-invites-send.component.html',
  styleUrls: ['./friend-invites-send.component.css']
})
export class FriendInvitesSendComponent implements OnInit {

  user: User = new User();
  isUserLoaded = false;
  userFriendInviteSend: FriendInvite[];
  isUserFriendInviteSendLoaded = false;

  constructor(private userService: UserService,
              private notificationService: NotificationService,
              private friendService: FriendService) { }

  ngOnInit(): void {
    this.handleUserDetails();
  }

  private handleUserDetails() {
    this.userService.getCurrentUser()
      .subscribe(data => {
        this.user = data;
        this.isUserLoaded = true;

        this.friendService.getUserFriendInviteSendForUser(this.user.id).subscribe(
          data => {
            this.userFriendInviteSend = data;
            this.isUserFriendInviteSendLoaded = true;
          }
        )
      })
  }

  denyFriendInvite(id: number, i: number) {
    this.friendService.denyInvitation(id).subscribe(data => {
      this.notificationService.showSnackBar(data.message);
      this.userFriendInviteSend.splice(i, 1);
    }, error => {
      this.notificationService.showSnackBar(error);
      this.ngOnInit();
    })
  }
}
