import { Component, OnInit } from '@angular/core';
import {User} from "../../models/user";
import {Post} from "../../models/post";
import {Channel} from "../../models/channel";
import {UserService} from "../../service/user.service";
import {ChannelService} from "../../service/channel.service";
import {NotificationService} from "../../service/notification.service";

@Component({
  selector: 'app-channel-list',
  templateUrl: './channel-list.component.html',
  styleUrls: ['./channel-list.component.css']
})
export class ChannelListComponent implements OnInit {

  user: User = new User();
  isUserDataLoaded = false;
  channels: Channel[];
  isChannelsDataLoaded = false;

  constructor(private userService: UserService,
              private notificationService: NotificationService,
              private channelService: ChannelService) { }

  ngOnInit(): void {
    this.handleUserDetails();
  }

  private handleUserDetails() {
    this.userService.getCurrentUser()
      .subscribe(data => {
        this.user = data;
        this.isUserDataLoaded = true;

        this.channelService.getAllChannelsForUser()
          .subscribe(
          data => {
            this.channels = data;
            this.isChannelsDataLoaded = true;
          }
        )
      })
  }

  denyChannel(id: number, i: number) {
    this.channelService.delete(id).subscribe(data => {
      this.notificationService.showSnackBar('Channel was deleted!');
      this.channels.splice(i, 1);
    }, error => {
      this.notificationService.showSnackBar('Channel was not deleted!');
      this.ngOnInit();
    })
  }
}
