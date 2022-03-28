import {Component, Input, OnInit} from '@angular/core';
import {GroupAuthor} from "../../models/group-author";
import {UserAuthor} from "../../models/user-author";

@Component({
  selector: 'app-user-small-element',
  templateUrl: './user-small-element.component.html',
  styleUrls: ['./user-small-element.component.css']
})
export class UserSmallElementComponent implements OnInit {

  @Input() user: UserAuthor;
  constructor() { }

  ngOnInit(): void {
  }

}
