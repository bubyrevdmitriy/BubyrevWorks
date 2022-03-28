import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {NotificationService} from "../../service/notification.service";
import {TokenStorageService} from "../../service/token-storage.service";
import {AuthService} from "../../service/auth.service";
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  TOKEN_KEY = 'auth-token';
  status: string;
  isError = false;
  public loginFormGroup: FormGroup;

  constructor(private cookie: CookieService,
              private authService: AuthService,
              private tokenStorage: TokenStorageService,
              private notificationService: NotificationService,
              private router: Router,
              private fb: FormBuilder) {
    if (this.tokenStorage.getUser()) {
      // чтобы авторезированный пользователь не мог сного вернуться на страницу логина
      this.router.navigate(['feed']);
    }
  }

  ngOnInit(): void {
    this.tokenStorage.deleteAllCookies();
    this.loginFormGroup = this.createLoginForm();
  }

  private createLoginForm() {
    return this.fb.group({
      email: ['', Validators.compose([Validators.required,  Validators.email])],
      password: ['', Validators.compose([Validators.required])]
    });
  }

  submit(): void {
    this.authService.login({
      email: this.loginFormGroup.value.email,
      password: this.loginFormGroup.value.password
    }).subscribe(data => {
      this.tokenStorage.saveToken(data.token);
      this.tokenStorage.saveUser(data);

      //this.notificationService.showSnackBar('Successfully logged in');
      this.router.navigate(['/']);

      window.location.reload();
    }, error => {
      this.status = 'Incorrect email and password!';
      this.isError = true;
      //this.notificationService.showSnackBar(error.message);
    })
  }
}
