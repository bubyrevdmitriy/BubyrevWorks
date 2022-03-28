import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../service/token-storage.service";
import {ActivatedRoute, Router} from "@angular/router";
import {NotificationService} from "../../service/notification.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../service/auth.service";
// @ts-ignore
import { MatProgressSpinnerModule } from '@angular/material';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {
  public emailFormGroup: FormGroup;
  isSendingProcess: boolean = false;
  isMailSend: boolean = false;
  isMailSendSuccessfully: boolean = false;

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
    this.emailFormGroup = this.createEmailForm();
  }

  private createEmailForm() {
    return this.fb.group({
      email: ['', Validators.compose([Validators.required,  Validators.email])],
    });
  }

  submit() {
    this.isSendingProcess = true;
    this.authService.sendPasswordRestoreLetter(this.emailFormGroup.value.email)
      .subscribe(data => {
      this.isMailSend = true;
      this.isMailSendSuccessfully = true;
      this.isSendingProcess = false;
      this.notificationService.showSnackBar('Password restore letter was send!');
    }, error => {
      this.isMailSend = true;
      this.isMailSendSuccessfully = false;
      this.isSendingProcess = false;
      this.notificationService.showSnackBar('Password restore letter was not send, some error occurred!');
    })
  }
}
