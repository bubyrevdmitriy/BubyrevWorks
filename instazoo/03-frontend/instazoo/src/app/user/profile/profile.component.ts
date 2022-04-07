import {Component, OnChanges, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {PostService} from "../../service/post.service";
import {TokenStorageService} from "../../service/token-storage.service";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {UserService} from "../../service/user.service";
import {NotificationService} from "../../service/notification.service";
import {ImageService} from "../../service/image.service";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {EditUserComponent} from "../edit-user/edit-user.component";
import {Post} from "../../models/post";
import {Subject} from "rxjs";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  currentUserPageId;
  isUserDataLoaded = false;
  user: User;
  selectedFile: File;
  userProfileImage: File;
  previewImgURL: any;
  eventsSubject: Subject<Post> = new Subject<Post>();
  createPostPanelOpenState: boolean = false;

  constructor(private postService: PostService,
              private tokenStorageService: TokenStorageService,
              private dialog: MatDialog,
              private route: ActivatedRoute,
              private userService: UserService,
              private notificationService: NotificationService,
              private imageService: ImageService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.buildThePage();
    });
  }

  openUserEditDialog() {
    const dialogUserEditConfig = new MatDialogConfig();
    dialogUserEditConfig.width = '400px';
    dialogUserEditConfig.data = {
      user: this.user
    };
    this.dialog.open(EditUserComponent, dialogUserEditConfig);
  }

  onFileSelected(event): void {
    this.selectedFile = event.target.files[0];
    const reader = new FileReader();
    reader.readAsDataURL(this.selectedFile);
    reader.onload = () => {
      this.previewImgURL = reader.result;
    };
  }

  onUpload() {
    if (this.selectedFile != null) {
      this.imageService.uploadImageToUser(this.selectedFile)
        .subscribe(() => {
          this.notificationService.showSnackBar('Profile Image updated successfully!');
        });
    }
  }

  addNewPost($event: Post) {
    this.togglePanel();
    this.eventsSubject.next($event);
  }

  togglePanel() {
    this.createPostPanelOpenState = !this.createPostPanelOpenState;
  }

  private buildThePage() {
    this.currentUserPageId = +this.route.snapshot.paramMap.get('id');
    this.userService.getUserById(this.currentUserPageId).subscribe(
      data => {
        this.user = data;
        this.isUserDataLoaded = true;
      });
  }
}
