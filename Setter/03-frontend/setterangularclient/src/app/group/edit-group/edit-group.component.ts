import {Component, EventEmitter, Inject, OnInit, Output} from '@angular/core';
import {Group} from "../../models/group";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {NotificationService} from "../../service/notification.service";
import {GroupInviteService} from "../../service/group-invite.service";
import {GroupService} from "../../service/group.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {User} from "../../models/user";
import {ImageUploadService} from "../../service/image-upload.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-edit-group',
  templateUrl: './edit-group.component.html',
  styleUrls: ['./edit-group.component.css']
})
export class EditGroupComponent implements OnInit {
  group: Group = new Group();
  public groupUpdateForm: FormGroup;
  selectedFile: File;
  previewImgURL: any;
  isGroupInfoChanged: boolean = false;

  constructor(public matDialogRef: MatDialogRef<EditGroupComponent>,
              @Inject(MAT_DIALOG_DATA) public data,
              private fb: FormBuilder,
              private router: Router,
              private notificationService: NotificationService,
              private imageService: ImageUploadService,
              private groupService: GroupService
  ) {
    this.group = data.group;
  }

  ngOnInit(): void {
    this.groupUpdateForm = this.createGroupUpdateForm();
  }

  private createGroupUpdateForm() {
    return this.fb.group({
      name: ['', Validators.compose([Validators.required])],
      description: ['', Validators.compose([Validators.required])]
    });
  }

  submit() {
    const updatedGroup:  Group = this.updateGroup();
    this.groupService.updateUserGroup(updatedGroup, this.group.id)
      .subscribe(() => {
        this.notificationService.showSnackBar('UserGroup updated successfully!');
        this.onUpload();
        this.router.navigate(['/groups/'+this.group.id]);
        this.matDialogRef.close({isGroupInfoChanged: this.isGroupInfoChanged});
      });
  }

  deleteGroup() {
    this.groupService.deleteUserGroup(this.group.id)
      .subscribe(() => {
        this.notificationService.showSnackBar('UserGroup deleted successfully!');
      });
    this.router.navigate(['/groups']);
  }

  private updateGroup(): Group {
    if (this.group.name != this.groupUpdateForm.value.name || this.group.description != this.groupUpdateForm.value.description) {
      this.isGroupInfoChanged = true;
    }
    this.group.name = this.groupUpdateForm.value.name;
    this.group.description = this.groupUpdateForm.value.description;
    return this.group;
  }

  onUpload(): void {
    if (this.selectedFile != null) {
      this.isGroupInfoChanged = true;
      this.imageService.uploadImageToUserGroup(this.selectedFile, this.group.id)
        .subscribe(() => {
          this.notificationService.showSnackBar('Profile Image updated successfully!');
        });
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
