import {Comment} from "../../models/comment";
import {Component, EventEmitter, Input, OnInit, Output} from "@angular/core";
import {CommentService} from "../../service/comment.service";
import {Router} from "@angular/router";

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

  constructor(private commentService: CommentService, private router: Router) { }

  ngOnInit(): void {
  }

  deleteComment() {
    this.commentService.delete(this.comment.id)
      .subscribe(() => {
        this.deleteRequest.emit('delete');
      })
  }

  navigateToUserAuthor() {
    this.router.navigate(['/user/' + this.comment.author.id]);
  }
}
