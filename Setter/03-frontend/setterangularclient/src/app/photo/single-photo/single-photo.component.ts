import {Component, OnInit} from '@angular/core';
import {UserService} from "../../service/user.service";
import {ImageUploadService} from "../../service/image-upload.service";
import {ActivatedRoute, Router} from "@angular/router";
import {CommonImageInAlbum} from "../../models/common-image-in-album";
import {LikeService} from "../../service/like.service";
import {UserAuthor} from "../../models/user-author";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {UserLikedListMatDialogComponent} from "../../common/user-liked-list-mat-dialog/user-liked-list-mat-dialog.component";
import {NotificationService} from "../../service/notification.service";
import {CommentService} from "../../service/comment.service";

@Component({
  selector: 'app-single-photo',
  templateUrl: './single-photo.component.html',
  styleUrls: ['./single-photo.component.css']
})
export class SinglePhotoComponent implements OnInit {

  currentPageHostCategory: string;
  currentPageId: number;
  currentCommonImageId: number;
  commonImageInAlbum: CommonImageInAlbum;
  isCommonImageDataLoaded: Boolean = false;

  comments: Comment[];
  isCommentDataLoaded: boolean = false;

  constructor(private userService: UserService,
              private imageService:  ImageUploadService,
              private likeService: LikeService,
              private commentService: CommentService,
              private dialog: MatDialog,
              private router: Router,
              private notificationService: NotificationService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe( () => {
      this.handleUserDetails();
    })
  }

  private handleUserDetails() {
    this.currentPageHostCategory = this.route.snapshot.paramMap.get('hostCategory');
    this.currentPageId = +this.route.snapshot.paramMap.get('id');
    this.currentCommonImageId = +this.route.snapshot.paramMap.get('photoId');
    this.imageService.getImageById(this.currentCommonImageId)
      .subscribe(data => {
        this.commonImageInAlbum = data;
        this.isCommonImageDataLoaded = true;
      }, error => {
        this.notificationService.showSnackBar('Image is not available!');
      });
    this.commentService.getCommentsToCommonImage(this.currentCommonImageId)
      .subscribe(data => {
        if(data != null) {
          this.comments = data;
          this.isCommentDataLoaded = true;
        }
      })
  }

  likeCommonImage() {
    this.likeService.likeCommonImage(this.commonImageInAlbum.id).subscribe(data => {
      this.commonImageInAlbum.likedByLoginUser = true;
      this.commonImageInAlbum.likes = this.commonImageInAlbum.likes + 1;
    }, error => {
    })
  }

  disLikeCommonImage() {
    this.likeService.dislikeCommonImage(this.commonImageInAlbum.id).subscribe(data => {
      this.commonImageInAlbum.likedByLoginUser = false;
      this.commonImageInAlbum.likes = this.commonImageInAlbum.likes - 1;
    }, error => {
    })
  }

  openUserLikeToCommonImageList() {
    let userLikedList: UserAuthor[];
    this.likeService.getAllLikesForCommonImage(this.commonImageInAlbum.id).subscribe(data => {
      userLikedList = data;
      const dialogUserInviteConfig = new MatDialogConfig();
      dialogUserInviteConfig.width = '600px';
      dialogUserInviteConfig.data = {
        userLikedList: userLikedList,
        heading: 'User liked photo:'
      };
      this.dialog.open(UserLikedListMatDialogComponent, dialogUserInviteConfig);
    }, error => {
    })
  }

  deleteCommonImage() {
    this.imageService.deleteImage(this.commonImageInAlbum.id)
      .subscribe(() => {
        this.router.navigate(['/' + this.currentPageHostCategory + '/' + this.currentPageId + '/photos']);
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
}
