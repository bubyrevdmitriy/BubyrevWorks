import {AfterViewInit, Component, Inject, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {UserService} from "../../service/user.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {NotificationService} from "../../service/notification.service";
import {resolve} from "@angular/compiler-cli/src/ngtsc/file_system";
import {delay} from "rxjs/operators";
import {ImageUploadService} from "../../service/image-upload.service";

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit{

  user: User = new User();
  isUserDataLoaded = false;
  public profileUpdateForm: FormGroup;
  selectedFile: File;
  previewImgURL: any;
  userProfileImage: string;

  constructor(private fb: FormBuilder,
              private notificationService: NotificationService,
              private userService: UserService,
              private imageService: ImageUploadService) { }

  ngOnInit(): void {
    this.handleUserDetails();
    this.profileUpdateForm = this.createProfileForm();
  }

  private handleUserDetails() {
    this.userService.getCurrentUser()
      .subscribe(data => {
        this.user = data;
        this.isUserDataLoaded = true;
      });
    this.profileUpdateForm = this.createProfileForm();
  }

  private createProfileForm() {
    return this.fb.group({
      firstName: [this.user.firstName, Validators.compose([Validators.required])],
      middleName: [this.user.middleName],
      lastName: [this.user.lastName, Validators.compose([Validators.required])],
      bornDate: [this.user.bornDate, Validators.compose([Validators.required])],
      bio: [this.user.bio],
      phone: [this.user.phone]
    });
  }

  submit() {
    this.userService.updateUser(this.updateUser())
      .subscribe(() => {
        this.notificationService.showSnackBar('User updated successfully!');
        this.onUpload();
      });


  }

  private updateUser(): User {
    this.user.firstName = this.profileUpdateForm.value.firstName;
    this.user.middleName = this.profileUpdateForm.value.middleName;
    this.user.lastName = this.profileUpdateForm.value.lastName;
    this.user.bornDate = this.profileUpdateForm.value.bornDate;
    this.user.bio = this.profileUpdateForm.value.bio;
    this.user.phone = this.profileUpdateForm.value.phone;
    return this.user;
  }


  onUpload(): void {
    if (this.selectedFile != null) {
      this.imageService.uploadImageToUser(this.selectedFile)
        .subscribe(() => {
          window.location.reload();
          this.notificationService.showSnackBar('Profile Image updated successfully!');
        });
    } else {
      window.location.reload();
    }
  }

  onFileSelected(event) {
    this.selectedFile = event.target.files[0];
    const reader = new FileReader();
    reader.readAsDataURL(this.selectedFile);
    reader.onload = () => {
      this.previewImgURL = reader.result;
    };
  }
}
