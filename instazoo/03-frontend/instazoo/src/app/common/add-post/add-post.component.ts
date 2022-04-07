import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Post} from "../../models/post";
import {NotificationService} from "../../service/notification.service";
import {PostService} from "../../service/post.service";
import {UserService} from "../../service/user.service";
import {ImageService} from "../../service/image.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent implements OnInit {

  @Output()
  addPostRequest = new EventEmitter<Post>();

  panelOpenState = false;
  public postForm: FormGroup;
  selectedFile: File;
  postCreated: Post;
  previewImgURL: any;

  constructor(private fb: FormBuilder,
              private notificationService: NotificationService,
              private postService: PostService,
              private userService: UserService,
              private imageService: ImageService,
              private router: Router) { }

  ngOnInit(): void {
    this.postForm = this.createProfileForm();
  }

  private createProfileForm() {
    return this.fb.group({
      title: ['', Validators.compose([Validators.required])],
      caption: ['', Validators.compose([Validators.required])],
      location: ['', Validators.compose([Validators.required])]
    });
  }

  onFileSelected(event): void {
    this.selectedFile = event.target.files[0];
    const reader = new FileReader();
    reader.readAsDataURL(this.selectedFile);
    reader.onload = () => {
      this.previewImgURL = reader.result;
    };
  }

  submit(): void {
    console.log(this.postForm.value.title);
    this.postService.createPost({
      title: this.postForm.value.title,
      caption: this.postForm.value.caption,
      location: this.postForm.value.location,
    })
      .subscribe(data => {
        this.postCreated = data
        console.log(this.postCreated);

        if (this.selectedFile != null) {
          this.imageService.uploadImageToPost(this.selectedFile, this.postCreated.id)
            .subscribe(data => {
              this.postCreated.images.push(data);
              this.notificationService.showSnackBar('Post created successfully!');
              this.postForm.reset();
              this.selectedFile=null;
              this.previewImgURL=null;
              this.addPostRequest.emit(this.postCreated);
            })
        }
      })
  }

}
