import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Post} from "../../models/post";
import {VideoFile} from "../../models/video-file";
import {HttpClient} from "@angular/common/http";
import {VideoService} from "../../service/video.service";
import {GroupInviteService} from "../../service/group-invite.service";
import {UserService} from "../../service/user.service";
import {ActivatedRoute} from "@angular/router";
import {LikeService} from "../../service/like.service";
import {UserAuthor} from "../../models/user-author";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {UserLikedListMatDialogComponent} from "../../common/user-liked-list-mat-dialog/user-liked-list-mat-dialog.component";
import {NotificationService} from "../../service/notification.service";

@Component({
  selector: 'app-video-preview',
  templateUrl: './video-preview.component.html',
  styleUrls: ['./video-preview.component.css']
})
export class VideoPreviewComponent implements OnInit {

  @Input()
  videoFile: VideoFile;

  @Output()
  deleteRequest = new EventEmitter<string>();

  currentPageHostCategory: string;
  currentPageId: number;

  constructor(private http: HttpClient,
              private videoService: VideoService,
              private groupInviteService: GroupInviteService,
              private userService: UserService,
              private likeService: LikeService,
              private dialog: MatDialog,
              private notificationService: NotificationService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    let user = this.videoFile.userAuthor;
    let group = this.videoFile.userGroupAuthor;
    if ( user != null) {
      this.currentPageHostCategory = 'users';
      this.currentPageId = user.id;
      //this.isDataLoaded = true;
    }
    if ( group != null) {
      this.currentPageHostCategory = 'groups';
      this.currentPageId = group.id;
      //this.isDataLoaded = true;
    }
  }

  likeVideoFile() {
    this.likeService.likeVideoFile(this.videoFile.id).subscribe(data => {
      this.videoFile.likedByLoginUser = true;
      this.videoFile.likes = this.videoFile.likes + 1;
    }, error => {
    })
  }

  disLikeVideoFile() {
    this.likeService.dislikeVideoFile(this.videoFile.id).subscribe(data => {
      this.videoFile.likedByLoginUser = false;
      this.videoFile.likes = this.videoFile.likes - 1;
    }, error => {
    })
  }

  deleteVideoFile() {
    this.videoService.deleteVideoFile(this.videoFile.id)
      .subscribe(() => {
        this.notificationService.showSnackBar('Video was successfully deleted!');
        this.deleteRequest.emit('delete');
      }, error => {
        this.notificationService.showSnackBar('Video was not deleted! Some error occurred');
      });
  }

  openUserLikeToVideoFileList() {
    let userLikedList: UserAuthor[];
    this.likeService.getAllLikesForVideoFile(this.videoFile.id).subscribe(data => {
      userLikedList = data;
      const dialogUserInviteConfig = new MatDialogConfig();
      dialogUserInviteConfig.width = '600px';
      dialogUserInviteConfig.data = {
        userLikedList: userLikedList,
        heading: 'User liked video:'
      };
      this.dialog.open(UserLikedListMatDialogComponent, dialogUserInviteConfig);
    }, error => {
    })
  }
}
