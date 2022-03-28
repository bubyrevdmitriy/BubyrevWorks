import {Component, Input, OnInit} from '@angular/core';
import {GroupAuthor} from "../../models/group-author";

@Component({
  selector: 'app-group-small-element',
  templateUrl: './group-small-element.component.html',
  styleUrls: ['./group-small-element.component.css']
})
export class GroupSmallElementComponent implements OnInit {

  @Input() userGroup: GroupAuthor;
  constructor() { }

  ngOnInit(): void {
  }

}
