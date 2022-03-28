import {AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {NotificationService} from "../../service/notification.service";
import {VideoFile} from "../../models/video-file";
import {VideoService} from "../../service/video.service";
import {TokenStorageService} from "../../service/token-storage.service";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {LikeService} from "../../service/like.service";
import {CommentService} from "../../service/comment.service";
import {UserAuthor} from "../../models/user-author";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {UserLikedListMatDialogComponent} from "../../common/user-liked-list-mat-dialog/user-liked-list-mat-dialog.component";

@Component({
  selector: 'app-single-video',
  templateUrl: './single-video.component.html',
  styleUrls: ['./single-video.component.css']
})

export class SingleVideoComponent implements OnInit {

  currentPageHostCategory: string;
  currentPageId: number;
  currentVideoId: number;

  videoFile: VideoFile;
  isVideoLoaded: boolean = false;

  comments: Comment[];
  isCommentDataLoaded: boolean = false;

  constructor(private http: HttpClient,
              private dataService: VideoService,
              private likeService: LikeService,
              private commentService: CommentService,
              private tokenService: TokenStorageService,
              private router: Router,
              private notificationService: NotificationService,
              private dialog: MatDialog,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe( () => {
      this.currentPageHostCategory = this.route.snapshot.paramMap.get('hostCategory');
      this.currentPageId = +this.route.snapshot.paramMap.get('id');
      this.currentVideoId = +this.route.snapshot.paramMap.get('videoId');
      this.dataService.findById(this.currentVideoId)
        .then((vmd) => {
          this.videoFile = vmd;
          this.isVideoLoaded = true;
        });
      this.commentService.getCommentsToVideoFile(this.currentVideoId)
        .subscribe(data => {
          if(data != null) {
            this.comments = data;
            this.isCommentDataLoaded = true;
          }
        })
    })
  }

  addNewComment($event: any) {
    this.comments.push($event);
  }

  deleteComment($event: string, i: number) {
    if ($event == 'delete') {
      this.comments.splice(i, 1);
    }
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
    this.dataService.deleteVideoFile(this.currentVideoId)
      .subscribe(() => {
        this.notificationService.showSnackBar('Video was successfully deleted!');
        this.router.navigate(['/' + this.currentPageHostCategory + '/' + this.currentPageId + '/videos']);
      }, error => {
        this.notificationService.showSnackBar('Video was not deleted! Some error occurred');
      });
  }
}
