import {Component, OnInit} from '@angular/core';
import {Post} from "../../models/post";
import {UserService} from "../../service/user.service";
import {LikeService} from "../../service/like.service";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {ActivatedRoute, Router} from "@angular/router";
import {PostService} from "../../service/post.service";
import {NotificationService} from "../../service/notification.service";
import {UserAuthor} from "../../models/user-author";
import {UserLikedListMatDialogComponent} from "../user-liked-list-mat-dialog/user-liked-list-mat-dialog.component";
import {CommentService} from "../../service/comment.service";


@Component({
  selector: 'app-single-post',
  templateUrl: './single-post.component.html',
  styleUrls: ['./single-post.component.css']
})
export class SinglePostComponent implements OnInit {

  currentPageHostCategory: string;
  currentPageId: number;
  currentPostId: number;
  post: Post;
  isPostDataLoaded: Boolean = false;

  comments: Comment[];
  isCommentDataLoaded: boolean = false;

  constructor(private userService: UserService,
              private postService:  PostService,
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
    this.currentPostId = +this.route.snapshot.paramMap.get('postId');
    this.postService.getPostById(this.currentPostId)
      .subscribe(data => {
        if(data != null) {
          this.post = data;
          this.isPostDataLoaded = true;
        }
      }, error => {
        this.notificationService.showSnackBar('Post is not available!');
      });
    this.commentService.getCommentsToPost(this.currentPostId)
      .subscribe(data => {
        if(data != null) {
          this.comments = data;
          this.isCommentDataLoaded = true;
          console.log(this.comments);
        }
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
        this.router.navigate(['/' + this.currentPageHostCategory + '/' + this.currentPageId]);
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
