import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../service/token-storage.service";
import {ActivatedRoute, Router} from "@angular/router";
import {NotificationService} from "../../service/notification.service";
import {FormBuilder} from "@angular/forms";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-activation-user',
  templateUrl: './activation-user.component.html',
  styleUrls: ['./activation-user.component.css']
})
export class ActivationUserComponent implements OnInit {
  activationCode: string;
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
    this.activationCode = this.route.snapshot.paramMap.get('activationCode');
    this.authService.activateUser(this.activationCode)
      .subscribe(data => {
      this.isDataLoaded = true;
      this.notificationService.showSnackBar('User was successfully activated!');
      //window.location.reload();
    }, error => {
        this.router.navigate(['/']);
    })
  }
}
