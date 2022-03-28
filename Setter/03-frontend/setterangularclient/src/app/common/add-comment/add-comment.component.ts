import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Comment} from "../../models/comment";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NotificationService} from "../../service/notification.service";
import {ImageUploadService} from "../../service/image-upload.service";
import {CommentService} from "../../service/comment.service";

@Component({
  selector: 'app-add-comment',
  templateUrl: './add-comment.component.html',
  styleUrls: ['./add-comment.component.css']
})
export class AddCommentComponent implements OnInit {

  @Input()
  postId: number;

  @Input()
  commonImageId: number;

  @Input()
  videoFileId: number;

  @Output()
  addCommentRequest = new EventEmitter<Comment>();

  public commentForm: FormGroup;
  commentCreated: Comment;

  constructor(private fb: FormBuilder,
              private notificationService: NotificationService,
              private commentService: CommentService,
              private imageService: ImageUploadService) { }

  ngOnInit(): void {
    this.commentForm = this.createCommentForm();
  }

  private createCommentForm() {
    return this.fb.group({
      text: ['', Validators.compose([Validators.required])]
    });
  }

  get formValue(){
    return this.commentForm.controls;
  }

  submit(): void {
    if (this.postId != null) {
      this.commentService.addCommentToPost(this.postId, this.commentForm.value.text)
        .subscribe(data => {
          this.commentCreated = data;
          this.commentForm.reset();
          this.addCommentRequest.emit(this.commentCreated);
        })
    }
    if (this.commonImageId != null) {
      this.commentService.addCommentToCommonImage(this.commonImageId, this.commentForm.value.text)
        .subscribe(data => {
          this.commentCreated = data;
          this.commentForm.reset();
          this.addCommentRequest.emit(this.commentCreated);
        })
    }
    if (this.videoFileId != null) {
      this.commentService.addCommentToVideoFile(this.videoFileId, this.commentForm.value.text)
        .subscribe(data => {
          this.commentCreated = data;
          this.commentForm.reset();
          this.addCommentRequest.emit(this.commentCreated);
        })
    }
  }


}
