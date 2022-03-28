import {Component, Input, OnInit} from '@angular/core';
import {GroupAuthor} from "../../models/group-author";
import {GroupInvite} from "../../models/group-invite";

@Component({
  selector: 'app-group-invite-small-element',
  templateUrl: './group-invite-small-element.component.html',
  styleUrls: ['./group-invite-small-element.component.css']
})
export class GroupInviteSmallElementComponent implements OnInit {

  @Input() groupInvite: GroupInvite;
  @Input() description: String;
  constructor() { }

  ngOnInit(): void {
  }

}
