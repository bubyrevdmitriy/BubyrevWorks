import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../service/user.service";
import {GroupService} from "../../service/group.service";
import {GroupInviteService} from "../../service/group-invite.service";
import {NotificationService} from "../../service/notification.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ImageUploadService} from "../../service/image-upload.service";
import {Group} from "../../models/group";

@Component({
  selector: 'app-create-group',
  templateUrl: './create-group.component.html',
  styleUrls: ['./create-group.component.css']
})
export class CreateGroupComponent implements OnInit {

  currentUserId: number

  public userGroupFormGroup: FormGroup;

  selectedFile: File;
  previewImgURL: any;
  isPreviewImgURLLoaded = false;

  constructor(private userService: UserService,
              private groupService: GroupService,
              private groupInviteService: GroupInviteService,
              private notificationService: NotificationService,
              private router: Router,
              private fb: FormBuilder,
              private imageService: ImageUploadService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.currentUserId = +this.route.snapshot.paramMap.get('id');
    this.userGroupFormGroup = this.createGroupForm();
  }
  private createGroupForm() {
    return this.fb.group({
      name: ['', Validators.compose([Validators.required])],
      description: ['', Validators.compose([Validators.required])]
    });
  }

  onUpload(id: number): void {
    if (this.selectedFile != null) {
      this.imageService.uploadImageToUserGroup(this.selectedFile, id)
        .subscribe(() => {
          this.notificationService.showSnackBar('Successfully created new group');
          this.router.navigate(["/groups/" + id]);
        });
    }
  }

  onFileSelected(event) {
    this.selectedFile = event.target.files[0];
    const reader = new FileReader();
    reader.readAsDataURL(this.selectedFile);
    reader.onload = () => {
      this.previewImgURL = reader.result;
      this.isPreviewImgURLLoaded = true;
    };
  }

  submit() {
    let groupToCreate = new Group();
    groupToCreate.name = this.userGroupFormGroup.value.name;
    groupToCreate.description = this.userGroupFormGroup.value.description;
    this.groupService.createUserGroup(groupToCreate).subscribe(data => {
      if (this.isPreviewImgURLLoaded) {
        this.onUpload(data.id);
      } else {
        this.notificationService.showSnackBar('Successfully created new group');
        this.router.navigate(["/groups/" + data.id]);
      }

    }, error => {
      this.notificationService.showSnackBar('Something wrong during group creation!');
    })
  }
}
