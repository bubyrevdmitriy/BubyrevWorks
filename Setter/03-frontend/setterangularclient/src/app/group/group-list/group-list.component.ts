import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Subject} from "rxjs";

@Component({
  selector: 'app-group-list',
  templateUrl: './group-list.component.html',
  styleUrls: ['./group-list.component.css']
})
export class GroupListComponent implements OnInit {

  addNewGroupPair: Subject<any> = new Subject<any>();

  constructor(private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.handleUserDetails();
  }

  private handleUserDetails() {
  }

  enterInGroup($event: any) {
    this.emitEventToChild($event)
  }

  emitEventToChild(data: any) {
    this.addNewGroupPair.next(data);
  }
}
