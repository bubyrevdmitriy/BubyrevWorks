import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Post} from "../../models/post";
import {Subject} from "rxjs";

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  eventsSubject: Subject<string> = new Subject<string>();
  searchValue: string = null;

  constructor() { }

  ngOnInit(): void {
    this.eventsSubject.next(this.searchValue);
  }

  doSearch(value: string) {
    this.searchValue = value;
    this.eventsSubject.next(this.searchValue);
  }
}
