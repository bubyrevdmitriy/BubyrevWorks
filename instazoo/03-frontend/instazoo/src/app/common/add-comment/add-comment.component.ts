import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NotificationService} from "../../service/notification.service";
import {CommentService} from "../../service/comment.service";
import {ImageService} from "../../service/image.service";

@Component({
  selector: 'app-add-comment',
  templateUrl: './add-comment.component.html',
  styleUrls: ['./add-comment.component.css']
})
export class AddCommentComponent implements OnInit {

  @Input()
  postId: number;

  @Output()
  addCommentRequest = new EventEmitter<Comment>();

  public commentForm: FormGroup;
  commentCreated: Comment;

  constructor(private fb: FormBuilder,
              private notificationService: NotificationService,
              private commentService: CommentService,
              private imageService: ImageService) { }

  ngOnInit(): void {
    this.commentForm = this.createCommentForm();
  }

  private createCommentForm() {
    return this.fb.group({
      text: ['', Validators.compose([Validators.required])]
    });
  }

  submit(): void {
    this.commentService.addCommentToPost(this.postId, this.commentForm.value.text)
      .subscribe(data => {
        this.commentCreated = data;
        this.commentForm.reset();
        this.addCommentRequest.emit(this.commentCreated);
      })
  }
}
