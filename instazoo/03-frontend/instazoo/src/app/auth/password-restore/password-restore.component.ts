import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {TokenStorageService} from "../../service/token-storage.service";
import {NotificationService} from "../../service/notification.service";

@Component({
  selector: 'app-password-restore',
  templateUrl: './password-restore.component.html',
  styleUrls: ['./password-restore.component.css']
})
export class PasswordRestoreComponent implements OnInit {

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
      this.router.navigate(['main']);
    }
  }

  ngOnInit(): void {
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
        this.router.navigate(['/login']);
      }, error => {
        this.isMailSend = true;
        this.isMailSendSuccessfully = false;
        this.isSendingProcess = false;
        this.notificationService.showSnackBar('Password restore letter was not send, some error occurred!');
      })
  }
}
