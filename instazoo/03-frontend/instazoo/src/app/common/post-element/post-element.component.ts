import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Post} from "../../models/post";
import {LikeService} from "../../service/like.service";
import {PostService} from "../../service/post.service";
import {CommentService} from "../../service/comment.service";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {UserName} from "../../models/user-name";
import {UserLikedListMatDialogComponent} from "../user-liked-list-mat-dialog/user-liked-list-mat-dialog.component";
import {Router} from "@angular/router";

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

  commentPanelOpenState: boolean = false;

  comments: Comment[];
  isCommentDataLoaded: boolean = false;

  constructor(private likeService: LikeService,
              private postService: PostService,
              private commentService: CommentService,
              private router: Router,
              private dialog: MatDialog) { }

  ngOnInit(): void {
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

  openUserLikeToPostList() {
    let userLikedList: UserName[];
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

  toggleCommentPanel() {
    if (this.isCommentDataLoaded == false) {
      this.commentService.getCommentsToPost(this.post.id)
        .subscribe(data => {
          if(data != null) {
            this.comments = data;
            console.log(this.comments);
            this.isCommentDataLoaded = true;
          }
        })
    }
    this.commentPanelOpenState = !this.commentPanelOpenState;
  }

  deletePost() {
    this.postService.delete(this.post.id)
      .subscribe(() => {
        this.deleteRequest.emit('delete');
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

  navigateToUserAuthor() {
    this.router.navigate(['/user/' + this.post.author.id]);
  }
}
