import {Component, Input, OnInit} from '@angular/core';
import {GroupPair} from "../../models/group-pair";
import {GroupAuthor} from "../../models/group-author";
import {GroupService} from "../../service/group.service";
import {Observable, Subscription} from "rxjs";

@Component({
  selector: 'app-search-group-list',
  templateUrl: './search-group-list.component.html',
  styleUrls: ['./search-group-list.component.css']
})
export class SearchGroupListComponent implements OnInit {
  //@Input() searchValue: string;
  @Input() events: Observable<string>;
  searchValue: string;
  private eventsSubscription: Subscription;
  userGroups: GroupAuthor[];
  isUserGroupsLoaded = false;

  // properties for pagination
  pageIndex:number = 0;
  pageSize:number = 9;
  lowValue:number = 0;
  highValue:number = 50;
  totalGroups;

  constructor(private groupService: GroupService) { }

  ngOnInit(): void {
    this.handleGroupsDetails(this.searchValue);
    this.eventsSubscription = this.events.subscribe(data => {
      if(this.searchValue != data) {
        this.searchValue = data;
        this.handleGroupsDetails(this.searchValue);
      }
    });
  }

  ngOnDestroy() {
    this.eventsSubscription.unsubscribe();
  }

  private handleGroupsDetails(searchValue: string) {
    this.groupService.getAllUserGroups(searchValue, this.pageIndex, this.pageSize).subscribe(
      data => {
        this.userGroups = data;
        this.isUserGroupsLoaded = true;
      }
    );
    this.groupService.countAllUserGroups(searchValue)
      .subscribe(data => {
        this.totalGroups = data;
      });
  }

  getPaginatorData(event){
    console.log(event);
    if(event.pageIndex === this.pageIndex + 1){
      this.lowValue = this.lowValue + this.pageSize;
      this.highValue =  this.highValue + this.pageSize;
      this.pageIndex = event.pageIndex;
      this.handleGroupsDetails(this.searchValue);
    }
    else if(event.pageIndex === this.pageIndex - 1){
      this.lowValue = this.lowValue - this.pageSize;
      this.highValue =  this.highValue - this.pageSize;
      this.pageIndex = event.pageIndex;
      this.handleGroupsDetails(this.searchValue);
    }
    if(this.pageSize != event.pageSize) {
      this.pageSize = event.pageSize;
      this.handleGroupsDetails(this.searchValue);
    }
  }
}
