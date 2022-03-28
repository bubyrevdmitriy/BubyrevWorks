import {Component, Input, OnInit} from '@angular/core';
import {Post} from "../../models/post";
import {Channel} from "../../models/channel";
import {User} from "../../models/user";

@Component({
  selector: 'app-channel-element',
  templateUrl: './channel-element.component.html',
  styleUrls: ['./channel-element.component.css']
})
export class ChannelElementComponent implements OnInit {

  @Input()
  channel: Channel;

  @Input()
  user: User;
  constructor() { }

  ngOnInit(): void {
  }

}
