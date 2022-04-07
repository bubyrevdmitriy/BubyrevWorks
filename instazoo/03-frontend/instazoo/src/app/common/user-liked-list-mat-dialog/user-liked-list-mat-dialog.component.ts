import {Component, Inject, OnInit} from '@angular/core';
import {UserName} from "../../models/user-name";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-liked-list-mat-dialog',
  templateUrl: './user-liked-list-mat-dialog.component.html',
  styleUrls: ['./user-liked-list-mat-dialog.component.css']
})
export class UserLikedListMatDialogComponent implements OnInit {

  userLikedList: UserName[];
  heading: any;
  constructor(@Inject(MAT_DIALOG_DATA) public data, private router: Router) {
    this.userLikedList = data.userLikedList;
    this.heading = data.heading;
  }

  ngOnInit(): void {
  }

  navigateToUser(id: number) {
    this.router.navigate(['/user/' + id]);
  }

}
