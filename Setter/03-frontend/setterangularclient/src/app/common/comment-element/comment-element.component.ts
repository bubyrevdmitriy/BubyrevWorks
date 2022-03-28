import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Comment} from "../../models/comment";
import {LikeService} from "../../service/like.service";
import {PostService} from "../../service/post.service";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {CommentService} from "../../service/comment.service";
import {UserAuthor} from "../../models/user-author";
import {UserLikedListMatDialogComponent} from "../user-liked-list-mat-dialog/user-liked-list-mat-dialog.component";

@Component({
  selector: 'app-comment-element',
  templateUrl: './comment-element.component.html',
  styleUrls: ['./comment-element.component.css']
})
export class CommentElementComponent implements OnInit {

  @Input()
  comment: Comment;

  @Output()
  deleteRequest = new EventEmitter<string>();

  constructor(private likeService: LikeService,
              private commentService: CommentService,
              private postService: PostService,
              private dialog: MatDialog) { }

  ngOnInit(): void {
  }

  likeComment() {
    this.likeService.likeComment(this.comment.id).subscribe(data => {
      this.comment.likedByLoginUser = true;
      this.comment.likes = this.comment.likes + 1;
    }, error => {
    })
  }

  disLikeComment() {
    this.likeService.disLikeComment(this.comment.id).subscribe(data => {
      this.comment.likedByLoginUser = false;
      this.comment.likes = this.comment.likes - 1;
    }, error => {
    })
  }

  deleteComment() {
    this.commentService.delete(this.comment.id)
      .subscribe(() => {
        this.deleteRequest.emit('delete');
      })
  }

  openUserLikeToCommentList() {
    let userLikedList: UserAuthor[];
    this.likeService.getAllLikesForComment(this.comment.id).subscribe(data => {
      userLikedList = data;
      const dialogUserInviteConfig = new MatDialogConfig();
      dialogUserInviteConfig.width = '600px';
      dialogUserInviteConfig.data = {
        userLikedList: userLikedList,
        heading: 'User liked comment:'
      };
      this.dialog.open(UserLikedListMatDialogComponent, dialogUserInviteConfig);
    }, error => {
    })
  }
}
