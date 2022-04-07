import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../service/auth.service";
import {TokenStorageService} from "../../service/token-storage.service";
import {NotificationService} from "../../service/notification.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loginFormGroup: FormGroup;

  constructor(private authService: AuthService,
              private tokenStorage: TokenStorageService,
              private notificationService: NotificationService,
              private router: Router,
              private fb: FormBuilder
  ) {
    if (this.tokenStorage.getUser()) {
      // чтобы авторезированный пользователь не мог сного вернуться на страницу логина
      this.router.navigate(['main']);
    }
  }

  ngOnInit(): void {
    this.loginFormGroup = this.createLoginForm();
  }

  private createLoginForm() {
    return this.fb.group({
      username: ['', Validators.compose([Validators.required,  Validators.email])],
      password: ['', Validators.compose([Validators.required])]
    });
  }

  submit(): void {
    this.authService.login({
      username: this.loginFormGroup.value.username,
      password: this.loginFormGroup.value.password
    }).subscribe(data => {
      console.log(data);

      this.tokenStorage.saveToken(data.token);
      this.tokenStorage.saveUser(data);

      this.notificationService.showSnackBar('Successfully logged in');
      this.router.navigate(['/']);

      window.location.reload();
    }, error => {
      console.log(error);
      this.notificationService.showSnackBar(error.message);
    })
  }
}
