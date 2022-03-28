import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {TokenStorageService} from "../../service/token-storage.service";
import {NotificationService} from "../../service/notification.service";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  isError: boolean = false;
  public registerFormGroup: FormGroup;
  isRegistrationProcess: boolean = false;

  constructor(private authService: AuthService,
              private tokenStorage: TokenStorageService,
              private notificationService: NotificationService,
              private router: Router,
              private fb: FormBuilder) {
      if (this.tokenStorage.getUser()) {
      // чтобы авторезированный пользователь не мог сного зарегистрироваться
      this.router.navigate(['main']);
    }
  }

  ngOnInit(): void {
    this.registerFormGroup = this.createLoginForm();
  }

  private createLoginForm() {
    return this.fb.group({
      email: ['', Validators.compose([Validators.required, Validators.email])],
      firstName: ['', Validators.compose([Validators.required])],
      lastName: ['', Validators.compose([Validators.required])],
      bornDate: ['', Validators.compose([Validators.required])],
      password: ['', Validators.compose([Validators.required, Validators.minLength(6)])],
      confirmPassword: ['', Validators.compose([Validators.required, Validators.minLength(6)])]
    });
  }

  get formValue(){
    return this.registerFormGroup.controls;
  }

  submit() {
    if (this.registerFormGroup.value.password === this.registerFormGroup.value.confirmPassword) {
      this.isRegistrationProcess = true;
      this.authService.register({
        email: this.registerFormGroup.value.email,
        firstName: this.registerFormGroup.value.firstName,
        lastName: this.registerFormGroup.value.lastName,
        bornDate: this.registerFormGroup.value.bornDate,
        password: this.registerFormGroup.value.password,
        confirmPassword: this.registerFormGroup.value.confirmPassword
      }).subscribe(data => {
        this.isError = false;
        this.notificationService.showSnackBar('Successfully created new user! Activation mail was send on your email!');
        this.isRegistrationProcess = false;
        this.router.navigate(['/login']);
      }, error => {
        this.isError = true;
        this.isRegistrationProcess = false;
        this.notificationService.showSnackBar('Something wrong during registration!');
      })
    }
  }


}
