import {Component, HostListener, OnInit} from '@angular/core';
import {UserService} from "../../service/user.service";
import {PostService} from "../../service/post.service";
import {ImageUploadService} from "../../service/image-upload.service";
import {NotificationService} from "../../service/notification.service";
import {ActivatedRoute} from "@angular/router";
import {GroupService} from "../../service/group.service";
import {User} from "../../models/user";
import {Post} from "../../models/post";
import {CommonImage} from "../../models/common-image";
import {Group} from "../../models/group";
import {GroupInviteService} from "../../service/group-invite.service";
import {GroupInvite} from "../../models/group-invite";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {EditGroupComponent} from "../edit-group/edit-group.component";
import {GroupPairForUserGroup} from "../../models/group-pair-for-user-group";
import {GroupPageUsersListComponent} from "../group-page-users-list/group-page-users-list.component";
import {GroupPageAdminsListComponent} from "../group-page-admins-list/group-page-admins-list.component";
import {VideoFile} from "../../models/video-file";
import {AudioFile} from "../../models/audio-file";
import {AudioService} from "../../service/audio.service";
import {VideoService} from "../../service/video.service";

@Component({
  selector: 'app-group-profile',
  templateUrl: './group-profile.component.html',
  styleUrls: ['./group-profile.component.css']
})
export class GroupProfileComponent implements OnInit {

  public innerWidth: number;
  public commonImageAmount: number = 5;

  user: User = new User();
  isUserLoaded = false;
  group: Group = new Group();
  isGroupLoaded = false;
  posts: Post[];
  isPostsDataLoaded = false;
  commonImages: CommonImage[];
  isCommonImagesDataLoaded: Boolean = false;
  userInvites: GroupInvite[];
  isUserInvitesLoaded = false;
  userPairs: GroupPairForUserGroup[];
  isUserPairsLoaded = false;
  userPairAdmins: GroupPairForUserGroup[];
  isUserPairAdminsLoaded = false;

  videoFiles: VideoFile[];
  isVideoDataLoaded: boolean = false;

  audioFileArray: AudioFile[];
  isAudioFileArrayDataLoaded: boolean = false;

  createPostPanelOpenState: boolean = false;
  currentGroupId;

  // properties for pagination
  pageIndex:number = 0;
  pageSize:number = 9;
  lowValue:number = 0;
  highValue:number = 50;

  totalPosts;

  constructor(private userService: UserService,
              private postService: PostService,
              private audioService: AudioService,
              private videoService: VideoService,
              private groupService: GroupService,
              public dialog: MatDialog,
              private groupInviteService: GroupInviteService,
              private imageService:  ImageUploadService,
              private notificationService: NotificationService,
              private route: ActivatedRoute) { }

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
    this.currentGroupId = +this.route.snapshot.paramMap.get('id');
    this.groupService.getUserGroup(this.currentGroupId).subscribe(
      data => {
        this.group = data;
        this.isGroupLoaded = true;
      }
    )
    this.userService.getCurrentUser()
      .subscribe(data => {
        this.user = data;
        this.isUserLoaded = true;
      });

    this.handlePostsDetails();
    this.imageService.getAllImagesForUserGroupPaginate(this.currentGroupId, 0, 5)
      .subscribe(data => {
        this.commonImages = data;
        this.isCommonImagesDataLoaded = true;
      })
    this.groupInviteService.getUserInvitesForUserGroup(this.currentGroupId)
      .subscribe(data => {
        this.userInvites = data;
        this.isUserInvitesLoaded = true;

      })
    this.groupInviteService.getUserPairsForUserGroup(this.currentGroupId)
      .subscribe(data => {
        this.userPairs = data;
        this.isUserPairsLoaded = true;
      })
    this.groupInviteService.getUserPairAdminsForUserGroup(this.currentGroupId)
      .subscribe(data => {
        this.userPairAdmins = data;
        this.isUserPairAdminsLoaded = true;
      })
    this.audioService.findAllAudiosFromGroup(this.currentGroupId, 0, 5)
      .then(res => {
        this.audioFileArray = res;
        this.isAudioFileArrayDataLoaded = true;
      }).catch(err => {
      this.isVideoDataLoaded = false;
    })
    this.videoService.findAllPreviewsFromGroup(this.currentGroupId, 0, 5)
      .then(res => {
        this.videoFiles = res;
        this.isVideoDataLoaded = true;
      }).catch(err => {
      this.isVideoDataLoaded = false;
    });
  }

  endGroupMembership() {
    this.groupInviteService.endGroupMembership(this.group.id).subscribe(data => {
      this.notificationService.showSnackBar(data.message);
      this.ngOnInit();
    }, error => {
      this.notificationService.showSnackBar(error);
    })
  }

  acceptGroupInvite() {
    this.groupInviteService.acceptAllUserGroupInvitation(this.group.id).subscribe(data => {
      this.notificationService.showSnackBar('Successfully enter to the group!');
      this.group.isLoginUserMember = true;
      this.group.isLoginUserHasInvite = false;
    }, error => {
      this.notificationService.showSnackBar('You cannot enter to the group, some error occurred!');
    })
  }

  denyGroupInvite() {
    this.groupInviteService.denyAllUserGroupInvitation(this.group.id).subscribe(data => {
      this.group.isLoginUserMember = false;
      this.group.isLoginUserHasInvite = false;
      this.notificationService.showSnackBar(data.message);
    }, error => {
      this.notificationService.showSnackBar(error);
    })
  }

  enterGroup() {
    this.groupInviteService.enterToGroup(this.group.id).subscribe(data => {
      this.notificationService.showSnackBar(data.message);
      this.ngOnInit();
    }, error => {
      this.notificationService.showSnackBar(error);
    })
  }

  endGroupAdminShip() {
    this.groupInviteService.endAdmin(this.group.id).subscribe(data => {
      this.notificationService.showSnackBar(data.message);
      this.ngOnInit();
    }, error => {
      this.notificationService.showSnackBar(error);
    })
  }


  openEditGroupComponent(): void {
    const dialogGroupEditConfig = new MatDialogConfig();
    dialogGroupEditConfig.width = '900px';
    dialogGroupEditConfig.data = {
      group: this.group,
    };
    const dialogRef = this.dialog.open(EditGroupComponent, dialogGroupEditConfig);
    dialogRef.afterClosed().subscribe(result => {
      if(result.isGroupInfoChanged === true){
        this.ngOnInit();
      }else {}
    });
  }


  showGroupMembersDialog() {
    const dialogUserInviteConfig = new MatDialogConfig();
    dialogUserInviteConfig.width = '600px';
    dialogUserInviteConfig.data = {
      group: this.group,
      userPairs: this.userPairs,
      heading: 'Group ' + this.group.name + ' members:'
    };
    const dialogRef = this.dialog.open(GroupPageUsersListComponent, dialogUserInviteConfig);
    dialogRef.afterClosed().subscribe(result => {
      if(result.isGroupInfoChanged === true){
        this.ngOnInit();
      }else {}
    });
  }

  showGroupAdminsDialog() {
    const dialogUserInviteConfig = new MatDialogConfig();
    dialogUserInviteConfig.width = '600px';
    dialogUserInviteConfig.data = {
      group: this.group,
      userPairAdmins: this.userPairAdmins,
      heading: 'Group ' + this.group.name + ' admins:'
    };
    this.dialog.open(GroupPageAdminsListComponent, dialogUserInviteConfig);
  }

  deletePost($event: string, i: number) {
    if ($event == 'delete') {
      this.posts.splice(i, 1);
    }
  }

  togglePanel() {
    this.createPostPanelOpenState = !this.createPostPanelOpenState;
  }

  addNewPost($event: Post) {
    this.posts.splice(0, 0, $event);
    this.togglePanel();
  }

  getPaginatorData(event){
    console.log(event);
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
    this.postService.getPostsForUserGroupPaginate(this.currentGroupId, this.pageIndex, this.pageSize).subscribe(
      data => {
        this.posts = data;
        this.isPostsDataLoaded = true;
      }
    );
    this.postService.countPostsForUserGroup(this.currentGroupId)
      .subscribe(data => {
        this.totalPosts = data;
      });
  }
}
