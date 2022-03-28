import {Component, HostListener, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {TokenStorageService} from "../../service/token-storage.service";
import {UserService} from "../../service/user.service";
import {Router} from "@angular/router";
import {MatExpansionModule} from '@angular/material/expansion';

@Component({
  selector: 'app-side-menu-small',
  templateUrl: './side-menu-small.component.html',
  styleUrls: ['./side-menu-small.component.css']
})
export class SideMenuSmallComponent implements OnInit {

  isLoggedIn = false;
  panelOpenState: boolean = false;


  constructor(private tokenService: TokenStorageService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenService.getToken();
  }

  togglePanel() {
    this.panelOpenState = !this.panelOpenState
  }

}
