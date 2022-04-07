import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {PostService} from "../../service/post.service";
import {Post} from "../../models/post";
import {Observable, Subscription} from "rxjs";

@Component({
  selector: 'app-user-posts',
  templateUrl: './user-posts.component.html',
  styleUrls: ['./user-posts.component.css']
})
export class UserPostsComponent implements OnInit, OnChanges {

  @Input() events: Observable<Post>;
  private eventsSubscription: Subscription;

  @Input()
  currentUserPageId: number;

  isPostsDataLoaded = false;
  posts: Post[];

  constructor(private route: ActivatedRoute,
              private postService: PostService) { }

  ngOnInit(): void {
    this.handlePostsDetails(this.currentUserPageId);
    this.eventsSubscription = this.events.subscribe(data => {
      this.posts.splice(0, 0, data);
    });
  }

  deletePost($event: string, i: number) {
    if ($event == 'delete') {
      this.posts.splice(i, 1);
    }
  }

  private handlePostsDetails(i: number) {
    this.postService.getPostForUser(i)
      .subscribe(data => {
        this.posts = data;
        this.isPostsDataLoaded = true;
      })
  }

  ngOnChanges(changes: SimpleChanges): void {
    if(changes['currentUserPageId']) {
      this.handlePostsDetails(this.currentUserPageId);
    }
  }
}
