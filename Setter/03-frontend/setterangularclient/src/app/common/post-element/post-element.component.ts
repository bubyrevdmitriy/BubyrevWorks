import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Post} from "../../models/post";
import {LikeService} from "../../service/like.service";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {UserLikedListMatDialogComponent} from "../user-liked-list-mat-dialog/user-liked-list-mat-dialog.component";
import {UserAuthor} from "../../models/user-author";
import {PostService} from "../../service/post.service";
import {CommentService} from "../../service/comment.service";

@Component({
  selector: 'app-post-element',
  templateUrl: './post-element.component.html',
  styleUrls: ['./post-element.component.css']
})
export class PostElementComponent implements OnInit {

  @Input()
  post: Post;

  @Output()
  deleteRequest = new EventEmitter<string>();

  currentPageHostCategory: string;
  currentPageId: number;
  isDataLoaded: boolean = false;

  globalI: number = 0;
  commentPanelOpenState: boolean = false;

  comments: Comment[];
  isCommentDataLoaded: boolean = false;

  constructor(private likeService: LikeService,
              private postService: PostService,
              private commentService: CommentService,
              private dialog: MatDialog) {
  }

  ngOnInit() {
    let user = this.post.userAuthor;
    let group = this.post.userGroupAuthor;
    if ( user != null) {
      this.currentPageHostCategory = 'users';
      this.currentPageId = user.id;
      this.isDataLoaded = true;
    }
    if ( group != null) {
      this.currentPageHostCategory = 'groups';
      this.currentPageId = group.id;
      this.isDataLoaded = true;
    }
  }

  likePost() {
    this.likeService.likePost(this.post.id).subscribe(data => {
      this.post.likedByLoginUser = true;
      this.post.likes = this.post.likes + 1;
    }, error => {
    })
  }

  disLikePost() {
    this.likeService.dislikePost(this.post.id).subscribe(data => {
      this.post.likedByLoginUser = false;
      this.post.likes = this.post.likes - 1;
    }, error => {
    })
  }

  deletePost() {
    this.postService.delete(this.post.id)
      .subscribe(() => {
        this.deleteRequest.emit('delete');
      })
  }

  openUserLikeToPostList() {
    let userLikedList: UserAuthor[];
    this.likeService.getAllLikesForPost(this.post.id).subscribe(data => {
      userLikedList = data;
      const dialogUserInviteConfig = new MatDialogConfig();
      dialogUserInviteConfig.width = '600px';
      dialogUserInviteConfig.data = {
        userLikedList: userLikedList,
        heading: 'User liked post:'
      };
      this.dialog.open(UserLikedListMatDialogComponent, dialogUserInviteConfig);
    }, error => {
    })
  }

  changeImage(i: number) {
    this.globalI = i;
  }

  toggleCommentPanel() {
    if (this.isCommentDataLoaded == false) {
      this.commentService.getCommentsToPost(this.post.id)
        .subscribe(data => {
          if(data != null) {
            this.comments = data;
            this.isCommentDataLoaded = true;
          }
        })
    }
    this.commentPanelOpenState = !this.commentPanelOpenState;
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
