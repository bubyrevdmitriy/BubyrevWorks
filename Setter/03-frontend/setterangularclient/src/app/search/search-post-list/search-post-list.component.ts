import {Component, Input, OnInit} from '@angular/core';
import {Post} from "../../models/post";
import {PostService} from "../../service/post.service";
import {Observable, Subscription} from "rxjs";

@Component({
  selector: 'app-search-post-list',
  templateUrl: './search-post-list.component.html',
  styleUrls: ['./search-post-list.component.css']
})
export class SearchPostListComponent implements OnInit {
  //@Input() searchValue: string;
  @Input() events: Observable<string>;
  searchValue: string;
  private eventsSubscription: Subscription;
  isPostsDataLoaded = false;
  posts: Post[];

  // properties for pagination
  pageIndex:number = 0;
  pageSize:number = 9;
  lowValue:number = 0;
  highValue:number = 50;
  totalPosts;

  constructor(private postService: PostService) { }

  ngOnInit(): void {
    this.handlePostsDetails(this.searchValue);
    this.eventsSubscription = this.events.subscribe(data => {
      if(this.searchValue != data) {
        this.searchValue = data;
        this.handlePostsDetails(this.searchValue);
      }
    });
  }

  ngOnDestroy() {
    this.eventsSubscription.unsubscribe();
  }

  deletePost($event: string, i: number) {
    if ($event == 'delete') {
      this.posts.splice(i, 1);
    }
  }

  private handlePostsDetails(searchValue: string) {
    this.postService.getAllPostsPaginate(searchValue, this.pageIndex, this.pageSize).subscribe(
      data => {
        this.posts = data;
        this.isPostsDataLoaded = true;
      }
    );
    this.postService.countAllPosts(searchValue)
      .subscribe(data => {
        this.totalPosts = data;
      });
  }

  getPaginatorData(event){
    if(event.pageIndex === this.pageIndex + 1){
      this.lowValue = this.lowValue + this.pageSize;
      this.highValue =  this.highValue + this.pageSize;
      this.pageIndex = event.pageIndex;
      this.handlePostsDetails(this.searchValue);
    }
    else if(event.pageIndex === this.pageIndex - 1){
      this.lowValue = this.lowValue - this.pageSize;
      this.highValue =  this.highValue - this.pageSize;
      this.pageIndex = event.pageIndex;
      this.handlePostsDetails(this.searchValue);
    }
    if(this.pageSize != event.pageSize) {
      this.pageSize = event.pageSize;
      this.handlePostsDetails(this.searchValue);
    }
  }
}
