import {Component, HostListener, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {User} from "../../models/user";
import {UserService} from "../../service/user.service";
import {Post} from "../../models/post";
import {PostService} from "../../service/post.service";
import {CommonImage} from "../../models/common-image";
import {FriendPair} from "../../models/friend-pair";
import {FriendService} from "../../service/friend.service";
import {ImageUploadService} from "../../service/image-upload.service";
import {NotificationService} from "../../service/notification.service";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {GroupInviteComponent} from "../group-invite/group-invite.component";
import {GroupInviteService} from "../../service/group-invite.service";

import {UserPageFriendListComponent} from "../user-page-friend-list/user-page-friend-list.component";
import {UserPageGroupListComponent} from "../user-page-group-list/user-page-group-list.component";
import {GroupPairForUser} from "../../models/group-pair-for-user";
import {ChannelService} from "../../service/channel.service";
import {VideoFile} from "../../models/video-file";
import {AudioFile} from "../../models/audio-file";
import {AudioService} from "../../service/audio.service";
import {VideoService} from "../../service/video.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  public innerWidth: number;
  public commonImageAmount: number = 5;

  userPage: User = new User();
  isUserLoaded = false;
  posts: Post[];
  isPostsDataLoaded = false;
  commonImages: CommonImage[];
  isCommonImagesDataLoaded: Boolean = false;
  userFriendPairs: FriendPair[];
  isUserFriendPairsLoaded = false;
  userGroupPairs: GroupPairForUser[];
  isUserGroupPairsLoaded = false;

  videoFiles: VideoFile[];
  isVideoDataLoaded: boolean = false;

  audioFileArray: AudioFile[];
  isAudioFileArrayDataLoaded: boolean = false;

  createPostPanelOpenState: boolean = false;
  currentUserId;

  // properties for pagination
  pageIndex:number = 0;
  pageSize:number = 9;
  lowValue:number = 0;
  highValue:number = 50;

  totalPosts;
  //totalPages;

  constructor(private userService: UserService,
              private audioService: AudioService,
              private postService: PostService,
              private friendService: FriendService,
              private videoService: VideoService,
              private dialog: MatDialog,
              private imageService:  ImageUploadService,
              private notificationService: NotificationService,
              private channelService: ChannelService,
              private groupInviteService: GroupInviteService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe( () => {
      this.handleUserDetails();
    })

    this.innerWidth = window.innerWidth;
    if(this.innerWidth>1200) {
      this.commonImageAmount = 5;
    } else {
      this.commonImageAmount = 4;
    }
  }

  @HostListener('window:resize', ['$event'])
  onResize(event) {
    this.innerWidth = window.innerWidth;
    if(this.innerWidth>1200) {
      this.commonImageAmount = 5;
    } else {
      this.commonImageAmount = 4;
    }
  }

  private handleUserDetails() {
    this.currentUserId = +this.route.snapshot.paramMap.get('id');
    this.innerWidth = window.innerWidth;
    this.userService.getUserById(this.currentUserId).subscribe(
      data => {
        this.userPage = data;
        this.isUserLoaded = true;

        this.handlePostsDetails();
        this.imageService.getImagesForUserPaginate(this.currentUserId, 0, 5)
          .subscribe(data => {
              this.commonImages = data;
              this.isCommonImagesDataLoaded = true;
            }
          )
        this.friendService.getUserPairsForUser(this.currentUserId).subscribe(
          data => {
            this.userFriendPairs = data;
            this.isUserFriendPairsLoaded = true;
          }
        )
        this.groupInviteService.getUserPairsForUser(this.currentUserId).subscribe(
          data => {
            this.userGroupPairs = data;
            this.isUserGroupPairsLoaded = true;
          }
        )
        this.audioService.findAllAudiosFromUser(this.currentUserId, 0, 5)
          .then(res => {
            this.audioFileArray = res;
            this.isAudioFileArrayDataLoaded = true;
          }).catch(err => {
          this.isVideoDataLoaded = false;
        })
        this.videoService.findAllPreviewsFromUser(this.currentUserId, 0, 5)
          .then(res => {
            this.videoFiles = res;
            this.isVideoDataLoaded = true;
          }).catch(err => {
          this.isVideoDataLoaded = false;
        });
      }, error => {

      }
    );


  }

  endFriendshipWithUser() {
    this.friendService.endFriendship(this.userPage.id).subscribe(data => {
      this.notificationService.showSnackBar(data.message);
      this.userPage.isLoginUserFriend = false;
    }, error => {
      this.notificationService.showSnackBar(error);
      this.ngOnInit();
    })
  }

  acceptFriendInviteFromUser() {
    this.friendService.acceptInvitation(this.userPage.id).subscribe(data => {
      this.notificationService.showSnackBar(data.message);
      this.userPage.isLoginUserFriend = true;
      this.userPage.receiveFromLoginUserFriendInvite = false;
      this.userPage.sendToLoginUserFriendInvite = false;
    }, error => {
      this.notificationService.showSnackBar(error);
      this.ngOnInit();
    })
  }

  inviteUserToFriends() {
    this.friendService.inviteUserToMyFriend(this.userPage.id).subscribe(data => {
      this.notificationService.showSnackBar(data.message);
      this.userPage.receiveFromLoginUserFriendInvite = true;
    }, error => {
      this.notificationService.showSnackBar(error);
      this.ngOnInit();
    })
  }

  textMessageToUser() {
    if(this.userPage.channelIdWithLoginUserPage != null) {
      this.router.navigate(['channels/' + this.userPage.channelIdWithLoginUserPage]);
    } else {
      this.channelService.createChannel(this.userPage.id).subscribe(data => {
        this.router.navigate(['channels/' + data]);
      })
    }
  }

  denyFriendInvite() {
    this.friendService.denyInvitation(this.userPage.id).subscribe(data => {
      this.notificationService.showSnackBar(data.message);
      this.userPage.receiveFromLoginUserFriendInvite = false;
      this.userPage.sendToLoginUserFriendInvite = false;
    }, error => {
      this.notificationService.showSnackBar(error);
      this.ngOnInit();
    })
  }

  openInviteUserToGroupComponent(): void {
    const dialogUserInviteConfig = new MatDialogConfig();
    dialogUserInviteConfig.width = '600px';
    dialogUserInviteConfig.data = {
      userPage: this.userPage,
      heading: 'Invite user to group:'
    };
    this.dialog.open(GroupInviteComponent, dialogUserInviteConfig);
  }

  showUserFriendsDialog() {
    const dialogUserInviteConfig = new MatDialogConfig();
    dialogUserInviteConfig.width = '600px';
    let heading;
    if (this.userPage.isLoginUserPage) {
      heading = 'My friends:';
    } else {
      heading = this.userPage.firstName + ' friends:';
    }
    dialogUserInviteConfig.data = {
      userPage: this.userPage,
      userFriendPairs: this.userFriendPairs,
      heading: heading
    };
    //UserLikedListMatDialogComponent UserPageFriendListComponent
    this.dialog.open(UserPageFriendListComponent, dialogUserInviteConfig);
  }

  showUserGroupsDialog() {
    const dialogUserInviteConfig = new MatDialogConfig();
    dialogUserInviteConfig.width = '600px';
    let heading;
    if (this.userPage.isLoginUserPage) {
      heading = 'My groups:';
    } else {
      heading = this.userPage.firstName + ' groups:';
    }
    dialogUserInviteConfig.data = {
      userPage: this.userPage,
      userGroupPairs: this.userGroupPairs,
      heading: heading
    };
    this.dialog.open(UserPageGroupListComponent, dialogUserInviteConfig);
  }

  deletePost($event: any, i: number) {
    if ($event == 'delete') {
      this.posts.splice(i, 1);
    }
  }

  togglePanel() {
    this.createPostPanelOpenState = !this.createPostPanelOpenState;
  }

  addNewPost($event: Post) {
    this.posts.splice(0, 0, $event);
    this.togglePanel()
  }

  getPaginatorData(event){
    if(event.pageIndex === this.pageIndex + 1){
      this.lowValue = this.lowValue + this.pageSize;
      this.highValue =  this.highValue + this.pageSize;
      this.pageIndex = event.pageIndex;
      this.handlePostsDetails();
    }
    else if(event.pageIndex === this.pageIndex - 1){
      this.lowValue = this.lowValue - this.pageSize;
      this.highValue =  this.highValue - this.pageSize;
      this.pageIndex = event.pageIndex;
      this.handlePostsDetails();
    }
    if(this.pageSize != event.pageSize) {
      this.pageSize = event.pageSize;
      this.handlePostsDetails();
    }
  }

  private handlePostsDetails() {
    this.postService.getPostsForUserPaginate(this.currentUserId, this.pageIndex, this.pageSize).subscribe(
      data => {
        this.posts = data;
        this.isPostsDataLoaded = true;
      }
    );
    this.postService.countPostsForUser(this.currentUserId)
      .subscribe(data => {
        this.totalPosts = data;
      });
  }
}
