import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {FriendInvite} from "../../models/friend-invite";
import {FriendPair} from "../../models/friend-pair";
import {UserService} from "../../service/user.service";
import {FriendService} from "../../service/friend.service";
import {Subject} from "rxjs";

@Component({
  selector: 'app-friend-list',
  templateUrl: './friend-list.component.html',
  styleUrls: ['./friend-list.component.css']
})
export class FriendListComponent implements OnInit {

  user: User = new User();
  isUserLoaded = false;
  userFriendInviteSend: FriendInvite[];
  isUserFriendInviteSendLoaded = false;
  userFriendInviteReceived: FriendInvite[];
  isUserFriendInviteReceivedLoaded = false;
  userPairs: FriendPair[];
  isUserPairsLoaded = false;

  addNewFriendPair: Subject<any> = new Subject<any>();

  constructor(private userService: UserService,
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

        this.friendService.getUserFriendInviteSendForUser(this.user.id).subscribe(
          data => {
            this.userFriendInviteSend = data;
            this.isUserFriendInviteSendLoaded = true;
          }
        )

        this.friendService.getAllUserFriendInviteReceivedForUser(this.user.id).subscribe(
          data => {
            this.userFriendInviteReceived = data;
            this.isUserFriendInviteReceivedLoaded = true;
          }
        )

        this.friendService.getUserPairsForUser(this.user.id).subscribe(
          data => {
            this.userPairs = data;
            this.isUserPairsLoaded = true;
          })
      })
  }

  enterToFriends($event: any) {
    this.emitEventToChild($event);
  }

  emitEventToChild(data: any) {
    this.addNewFriendPair.next(data);
  }

}
