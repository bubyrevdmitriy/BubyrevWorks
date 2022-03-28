import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {User} from "../../models/user";
import {FriendInvite} from "../../models/friend-invite";
import {UserService} from "../../service/user.service";
import {FriendService} from "../../service/friend.service";
import {NotificationService} from "../../service/notification.service";

@Component({
  selector: 'app-friend-invites-received',
  templateUrl: './friend-invites-received.component.html',
  styleUrls: ['./friend-invites-received.component.css']
})
export class FriendInvitesReceivedComponent implements OnInit {

  user: User = new User();
  isUserLoaded = false;

  userFriendInviteReceived: FriendInvite[];
  isUserFriendInviteReceivedLoaded = false;

  @Output()
  addToMyFriendsRequest = new EventEmitter<any>();

  constructor(private userService: UserService,
              private notificationService: NotificationService,
              private friendService: FriendService
  ) { }

  ngOnInit(): void {
    this.handleUserDetails();
  }

  private handleUserDetails() {
    this.userService.getCurrentUser()
      .subscribe(data => {
        this.user = data;
        this.isUserLoaded = true;

        this.friendService.getAllUserFriendInviteReceivedForUser(this.user.id).subscribe(
          data => {
            this.userFriendInviteReceived = data;
            this.isUserFriendInviteReceivedLoaded = true;
          }
        )
      })
  }

  acceptFriendInviteFromUser(id: number, i: number) {
    this.friendService.acceptInvitation(id).subscribe(data => {
      this.notificationService.showSnackBar(data.message);
      this.userFriendInviteReceived.splice(i, 1);
      this.addToMyFriendsRequest.emit(data);
    }, error => {
      this.notificationService.showSnackBar(error);
      this.ngOnInit();
    })
  }

  denyFriendInvite(id: number, i: number) {
    this.friendService.denyInvitation(id).subscribe(data => {
      this.notificationService.showSnackBar(data.message);
      this.userFriendInviteReceived.splice(i, 1);
    }, error => {
      this.notificationService.showSnackBar(error);
      this.ngOnInit();
    })
  }

}
