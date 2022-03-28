import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {FriendInvite} from "../../models/friend-invite";
import {FriendPair} from "../../models/friend-pair";
import {UserService} from "../../service/user.service";
import {FriendService} from "../../service/friend.service";
import {Observable, Subscription} from "rxjs";
import {NotificationService} from "../../service/notification.service";

@Component({
  selector: 'app-my-friends',
  templateUrl: './my-friends.component.html',
  styleUrls: ['./my-friends.component.css']
})
export class MyFriendsComponent implements OnInit {

  @Input() eventsAddNewFriendPair: Observable<any>;
  private eventsSubscription: Subscription;

  user: User = new User();
  isUserLoaded = false;
  userPairs: FriendPair[];
  isUserPairsLoaded = false;

  constructor(private userService: UserService,
              private notificationService: NotificationService,
              private friendService: FriendService) { }

  ngOnInit(): void {
    this.handleUserDetails();
    this.eventsSubscription = this.eventsAddNewFriendPair.subscribe(data => {
      this.addNewFriendPair(data);
    });
  }

  private handleUserDetails() {
    this.userService.getCurrentUser()
      .subscribe(data => {
        this.user = data;
        this.isUserLoaded = true;

        this.friendService.getUserPairsForUser(this.user.id).subscribe(
          data => {
            this.userPairs = data;
            this.isUserPairsLoaded = true;
          })
      })
  }

  addNewFriendPair($event: any) {
    this.userPairs.splice(0, 0, $event);
  }

  denyFriendPair(id: number, i: number) {
    this.friendService.endFriendship(id).subscribe(data => {
      this.notificationService.showSnackBar(data.message);
      this.userPairs.splice(i, 1);
    }, error => {
      this.notificationService.showSnackBar(error);
      this.ngOnInit();
    })
  }
}
