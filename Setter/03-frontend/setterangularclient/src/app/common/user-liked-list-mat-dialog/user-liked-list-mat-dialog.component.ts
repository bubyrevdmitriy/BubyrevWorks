import {Component, Inject, OnInit} from '@angular/core';
import {UserAuthor} from "../../models/user-author";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";

@Component({
  selector: 'app-user-liked-list-mat-dialog',
  templateUrl: './user-liked-list-mat-dialog.component.html',
  styleUrls: ['./user-liked-list-mat-dialog.component.css']
})
export class UserLikedListMatDialogComponent implements OnInit {

  userLikedList: UserAuthor[];
  heading: any;
  constructor(@Inject(MAT_DIALOG_DATA) public data) {
    this.userLikedList = data.userLikedList;
    this.heading = data.heading;
  }

  ngOnInit(): void {
  }

}
