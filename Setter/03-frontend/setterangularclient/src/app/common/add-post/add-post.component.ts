import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Post} from "../../models/post";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NotificationService} from "../../service/notification.service";
import {PostService} from "../../service/post.service";
import {ImageUploadService} from "../../service/image-upload.service";
import {Group} from "../../models/group";
import {HttpClient} from "@angular/common/http";


@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent implements OnInit {

  @Input()
  group: Group;

  @Output()
  addPostRequest = new EventEmitter<Post>();

  images : string[] = [];
  selectedFiles: File[] = [];
  public postForm: FormGroup;
  postCreated: Post;


  constructor(private fb: FormBuilder,
              private notificationService: NotificationService,
              private postService: PostService,
              private http: HttpClient,
              private imageService: ImageUploadService) { }

  ngOnInit(): void {
    this.postForm = this.createCommentForm();
  }

  private createCommentForm() {
    return this.fb.group({
      caption: ['', Validators.compose([Validators.required])],
      file: [''],
      fileSource: ['']
    });
  }

  get formValue(){
    return this.postForm.controls;
  }

  onFileChange(event:any) {
    if (event.target.files && event.target.files[0]) {
      this.selectedFiles.push(event.target.files[0]);
      console.log(this.selectedFiles);
      var filesAmount = event.target.files.length;
      for (let i = 0; i < filesAmount; i++) {
        var reader = new FileReader();
        reader.onload = (event:any) => {
          // Push Base64 string
          this.images.push(event.target.result);
          this.patchValues();
        }
        reader.readAsDataURL(event.target.files[i]);
      }
    }
  }

  // Patch form Values
  patchValues(){
    this.postForm.patchValue({
      fileSource: this.images
    });
  }

  // Remove Image
  removeImage(url:any){
    let step;
    for (step = 0; step < this.images.length; step++) {
      if(this.images[step] == url) {
          //this.images = this.images.filter(img => (img != url));
          this.images.splice(step, 1);
          this.selectedFiles.splice(step, 1);
          this.patchValues();
          break;
      }
    }
  }

  // Submit Form Data
  submit(){
    let newPost: Post = new Post();
    newPost.caption = this.postForm.value.caption;

    if (this.group == null) {
      this.postService.createPost(newPost)
        .subscribe(data => {
          this.postCreated = data;
          if (this.selectedFiles.length>0) {
            this.postCreated.commonImages = [];
            let step;
            for (step = 0; step < this.selectedFiles.length; step++) {
              this.imageService.uploadImageToPost(this.selectedFiles[step], this.postCreated.id)
                .subscribe(data => {
                  this.postCreated.commonImages.push(data);
                  if(step == this.selectedFiles.length) {
                    this.endWork();
                  }
                })
            }
          } else {
            this.endWork();
          }
        })
    } else {
      this.postService.createPostInUserGroup(newPost, this.group.id)
        .subscribe(data => {
          this.postCreated = data;
          if (this.selectedFiles.length>0) {
            this.postCreated.commonImages = [];
            let step;
            for (step = 0; step < this.selectedFiles.length; step++) {
              this.imageService.uploadImageToPost(this.selectedFiles[step], this.postCreated.id)
                .subscribe(data => {
                  this.postCreated.commonImages.push(data);
                  if(step == this.selectedFiles.length) {
                    this.endWork();
                  }
                })
            }
          } else {
            this.endWork();
          }
        })
    }
  }

  endWork(): void {
    this.notificationService.showSnackBar('Post created successfully!');
    this.postForm.reset();
    this.images  = [];
    this.selectedFiles  = [];
    this.addPostRequest.emit(this.postCreated);
  }
}
