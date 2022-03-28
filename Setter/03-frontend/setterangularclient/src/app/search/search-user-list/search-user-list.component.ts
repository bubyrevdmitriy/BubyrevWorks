import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {UserService} from "../../service/user.service";
import {UserAuthor} from "../../models/user-author";
import {Observable, Subscription} from "rxjs";

@Component({
  selector: 'app-search-user-list',
  templateUrl: './search-user-list.component.html',
  styleUrls: ['./search-user-list.component.css']
})
export class SearchUserListComponent implements OnInit {
  //@Input() searchValue: string;
  @Input() events: Observable<string>;
  searchValue: string;
  private eventsSubscription: Subscription;
  users: UserAuthor[];
  isUserDataLoaded = false;

  // properties for pagination
  pageIndex:number = 0;
  pageSize:number = 9;
  lowValue:number = 0;
  highValue:number = 50;
  totalUsers;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.handleUserDetails(this.searchValue);
    this.eventsSubscription = this.events.subscribe(data => {
      if(this.searchValue != data) {
        this.searchValue = data;
        this.handleUserDetails(this.searchValue);
      }
    });
  }

  ngOnDestroy() {
    this.eventsSubscription.unsubscribe();
  }

  private handleUserDetails(searchValue: string) {
    this.userService.getAllUsers(searchValue, this.pageIndex, this.pageSize)
      .subscribe(data => {
        this.users = data;
        this.isUserDataLoaded = true;
      });
    this.userService.countAllUsers(searchValue)
      .subscribe(data => {
        this.totalUsers = data;
      });
  }

  getPaginatorData(event){
    if(event.pageIndex === this.pageIndex + 1){
      this.lowValue = this.lowValue + this.pageSize;
      this.highValue =  this.highValue + this.pageSize;
      this.pageIndex = event.pageIndex;
      this.handleUserDetails(this.searchValue);
    }
    else if(event.pageIndex === this.pageIndex - 1){
      this.lowValue = this.lowValue - this.pageSize;
      this.highValue =  this.highValue - this.pageSize;
      this.pageIndex = event.pageIndex;
      this.handleUserDetails(this.searchValue);
    }
    if(this.pageSize != event.pageSize) {
      this.pageSize = event.pageSize;
      this.handleUserDetails(this.searchValue);
    }
  }
}
