import { Component, OnInit } from '@angular/core';
import {PostService} from "../../service/post.service";
import {Post} from "../../models/post";

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  isPostsDataLoaded = false;
  posts: Post[];
  createPostPanelOpenState: boolean = false;

  constructor(private postService: PostService) { }

  ngOnInit(): void {
    this.postService.getAllPosts()
      .subscribe(data => {
        console.log(data);
        this.posts = data;
        this.isPostsDataLoaded = true;
      })
  }

  deletePost($event: string, i: number) {
    if ($event == 'delete') {
      this.posts.splice(i, 1);
    }
  }

  addNewPost($event: Post) {
    this.posts.splice(0, 0, $event);
    this.togglePanel();
  }

  togglePanel() {
    this.createPostPanelOpenState = !this.createPostPanelOpenState;
  }
}
