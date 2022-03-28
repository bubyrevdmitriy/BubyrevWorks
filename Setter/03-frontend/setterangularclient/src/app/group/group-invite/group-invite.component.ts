import {Component, Input, OnInit} from '@angular/core';
import {GroupAuthor} from "../../models/group-author";
import {GroupInvite} from "../../models/group-invite";

@Component({
  selector: 'app-group-invite',
  templateUrl: './group-invite.component.html',
  styleUrls: ['./group-invite.component.css']
})
export class GroupInviteComponent implements OnInit {

  @Input() groupInvite: GroupInvite;
  constructor() { }

  ngOnInit(): void {
  }

}
