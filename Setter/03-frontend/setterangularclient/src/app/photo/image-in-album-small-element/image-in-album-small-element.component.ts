import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Post} from "../../models/post";
import {CommonImageInAlbum} from "../../models/common-image-in-album";
import {LikeService} from "../../service/like.service";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {UserAuthor} from "../../models/user-author";
import {UserLikedListMatDialogComponent} from "../../common/user-liked-list-mat-dialog/user-liked-list-mat-dialog.component";
import {ImageUploadService} from "../../service/image-upload.service";

@Component({
  selector: 'app-image-in-album-small-element',
  templateUrl: './image-in-album-small-element.component.html',
  styleUrls: ['./image-in-album-small-element.component.css']
})
export class ImageInAlbumSmallElementComponent implements OnInit {

  @Input()
  commonImageInAlbum: CommonImageInAlbum;
  @Input()
  currentPageHostCategory: string;
  @Input()
  currentPageId: number;
  @Output()
  deleteRequest = new EventEmitter<string>();

  constructor(private likeService: LikeService,
              private imageUploadService: ImageUploadService,
              private dialog: MatDialog) { }

  ngOnInit(): void {
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

  deleteCommonImage() {
    this.imageUploadService.deleteImage(this.commonImageInAlbum.id)
      .subscribe(() => {
        this.deleteRequest.emit('delete');
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
}
