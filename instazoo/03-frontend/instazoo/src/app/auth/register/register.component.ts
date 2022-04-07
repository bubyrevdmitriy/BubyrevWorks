import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../service/auth.service";
import {TokenStorageService} from "../../service/token-storage.service";
import {NotificationService} from "../../service/notification.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public registerFormGroup: FormGroup;

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
    this.registerFormGroup = this.createRegisterForm();
  }

  private createRegisterForm() {
    return this.fb.group({
      email: ['', Validators.compose([Validators.required, Validators.email])],
      firstname: ['', Validators.compose([Validators.required])],
      lastname: ['', Validators.compose([Validators.required])],
      username: ['', Validators.compose([Validators.required])],
      password: ['', Validators.compose([Validators.required])],
      confirmPassword: ['', Validators.compose([Validators.required])]
    });
  }

  submit() {
    this.authService.register({
      email: this.registerFormGroup.value.email,
      firstname: this.registerFormGroup.value.firstname,
      lastname: this.registerFormGroup.value.lastname,
      username: this.registerFormGroup.value.username,
      password: this.registerFormGroup.value.password,
      confirmPassword: this.registerFormGroup.value.confirmPassword
    }).subscribe(data => {
      console.log(data);
      this.notificationService.showSnackBar('Successfully created new user');
      this.router.navigate(['/login']);
    }, error => {
      console.log(error);
      this.notificationService.showSnackBar('Something wrong during registration!');
    })
  }

}
