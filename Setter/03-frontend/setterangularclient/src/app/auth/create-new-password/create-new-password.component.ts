import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../service/token-storage.service";
import {ActivatedRoute, Router} from "@angular/router";
import {NotificationService} from "../../service/notification.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-create-new-password',
  templateUrl: './create-new-password.component.html',
  styleUrls: ['./create-new-password.component.css']
})
export class CreateNewPasswordComponent implements OnInit {
  public newPasswordFormGroup: FormGroup;
  newPasswordCode: string;
  isDataLoaded: boolean = false;

  constructor(private authService: AuthService,
              private router: Router,
              private tokenStorage: TokenStorageService,
              private notificationService: NotificationService,
              private route: ActivatedRoute,
              private fb: FormBuilder
  ) {
    if (this.tokenStorage.getUser()) {
      // чтобы авторезированный пользователь не мог сного вернуться на страницу логина
      this.router.navigate(['feed']);
    }
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe( () => {
      this.handlePageDetails();
    })
  }

  private handlePageDetails() {
    this.newPasswordCode = this.route.snapshot.paramMap.get('newPasswordCode');
    this.authService.checkUserPasswordRestoreCode(this.newPasswordCode)
      .subscribe(data => {
        this.isDataLoaded = true;
        this.newPasswordFormGroup = this.createNewPasswordForm();
        //this.notificationService.showSnackBar('User was successfully activated!');
        //window.location.reload();
      }, error => {
        this.router.navigate(['/']);
      })

  }

  private createNewPasswordForm() {
    return this.fb.group({
      password: ['', Validators.compose([Validators.required, Validators.minLength(6)])],
      confirmPassword: ['', Validators.compose([Validators.required, Validators.minLength(6)])]
    });
  }

  get formValue(){
    return this.newPasswordFormGroup.controls;
  }

  submit() {
    if (this.newPasswordFormGroup.value.password === this.newPasswordFormGroup.value.confirmPassword) {
      this.authService.changeUserPassword(this.newPasswordCode,{
        password: this.newPasswordFormGroup.value.password,
        confirmPassword: this.newPasswordFormGroup.value.confirmPassword
      }).subscribe(data => {
        this.notificationService.showSnackBar('Successfully changed password!');
        this.router.navigate(['/login']);
      }, error => {
        this.notificationService.showSnackBar('Something wrong during password changing! Passwords are not the same');
      })
    }
  }


}
