import { Component, OnInit } from '@angular/core';
import {Post} from "../../models/post";
import {PostService} from "../../service/post.service";
import {User} from "../../models/user";
import {UserService} from "../../service/user.service";
import {FeedService} from "../../service/feed.service";

@Component({
  selector: 'app-feed-list',
  templateUrl: './feed-list.component.html',
  styleUrls: ['./feed-list.component.css']
})
export class FeedListComponent implements OnInit {

  posts: Post[];
  isPostsDataLoaded = false;

  // properties for pagination
  pageIndex:number = 0;
  pageSize:number = 9;
  lowValue:number = 0;
  highValue:number = 50;
  totalPosts;

  constructor(private feedService: FeedService) { }

  ngOnInit(): void {
    this.handleFeedDetails();
  }

  private handleFeedDetails() {

    this.feedService.getAllFeed(this.pageIndex, this.pageSize).subscribe(
      data => {
        this.posts = data;
        this.isPostsDataLoaded = true;
      });
    this.feedService.countAllFeed()
      .subscribe(data => {
        this.totalPosts = data;
      });
  }

  deletePost($event: string, i: number) {
    if ($event == 'delete') {
      this.posts.splice(i, 1);
    }
  }

  getPaginatorData(event){
    if(event.pageIndex === this.pageIndex + 1){
      this.lowValue = this.lowValue + this.pageSize;
      this.highValue =  this.highValue + this.pageSize;
      this.pageIndex = event.pageIndex;
      this.handleFeedDetails();
    }
    else if(event.pageIndex === this.pageIndex - 1){
      this.lowValue = this.lowValue - this.pageSize;
      this.highValue =  this.highValue - this.pageSize;
      this.pageIndex = event.pageIndex;
      this.handleFeedDetails();
    }
    if(this.pageSize != event.pageSize) {
      this.pageSize = event.pageSize;
      this.handleFeedDetails();
    }
  }
}
