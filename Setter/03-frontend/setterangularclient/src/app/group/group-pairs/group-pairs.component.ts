import {Component, Input, OnInit} from '@angular/core';
import {UserService} from "../../service/user.service";
import {GroupService} from "../../service/group.service";
import {GroupInviteService} from "../../service/group-invite.service";
import {GroupPair} from "../../models/group-pair";
import {User} from "../../models/user";
import {Observable, Subscription} from "rxjs";
import {NotificationService} from "../../service/notification.service";

@Component({
  selector: 'app-group-pairs',
  templateUrl: './group-pairs.component.html',
  styleUrls: ['./group-pairs.component.css']
})
export class GroupPairsComponent implements OnInit {

  @Input() eventsAddNewGroupPair: Observable<any>;
  private eventsSubscription: Subscription;

  user: User = new User();
  isUserLoaded = false;

  userGroupPairs: GroupPair[];
  isUserGroupPairsLoaded = false;

  constructor(private userService: UserService,
              private groupService: GroupService,
              private notificationService: NotificationService,
              private groupInviteService: GroupInviteService
  ) { }

  ngOnInit(): void {
    this.handleUserDetails();
    this.eventsSubscription = this.eventsAddNewGroupPair.subscribe(data => {
      this.addNewGroupPair(data);
    });
  }

  addNewGroupPair($event: any) {
    this.userGroupPairs.splice(0, 0, $event);
  }

  private handleUserDetails() {
    this.userService.getCurrentUser()
      .subscribe(data => {
        this.user = data;
        this.isUserLoaded = true;

        this.groupInviteService.getUserPairsForUser(this.user.id).subscribe(
          data => {
            this.userGroupPairs = data;
            console.log(this.userGroupPairs);
            this.isUserGroupPairsLoaded = true;
          }
        )
      })
  }

  denyGroupPair(id: number, i: number) {
    this.groupInviteService.endGroupMembership(id).subscribe(data => {
      this.notificationService.showSnackBar(data.message);
      this.userGroupPairs.splice(i, 1);
    }, error => {
      this.notificationService.showSnackBar(error);
      this.ngOnInit();
    })
  }

}
