import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {VideoFile} from "../../models/video-file";
import {HttpClient} from "@angular/common/http";
import {VideoService} from "../../service/video.service";
import {GroupInviteService} from "../../service/group-invite.service";
import {UserService} from "../../service/user.service";
import {ActivatedRoute} from "@angular/router";
import {Observable, Subscription} from "rxjs";

@Component({
  selector: 'app-search-video-list',
  templateUrl: './search-video-list.component.html',
  styleUrls: ['./search-video-list.component.css']
})
export class SearchVideoListComponent implements OnInit {

  //@Input() searchValue: string;
  @Input() events: Observable<string>;
  searchValue: string;
  private eventsSubscription: Subscription;
  loginUser: User = new User();
  isUserDataLoaded: boolean = false;

  videoFiles: VideoFile[];
  isVideoDataLoaded: boolean = false;

  imageToShow: any;
  tabIndex = 0;

  // properties for pagination
  pageIndex:number = 0;
  pageSize:number = 9;
  lowValue:number = 0;
  highValue:number = 50;
  totalVideos;

  constructor(private http: HttpClient,
              private videoService: VideoService,
              private groupInviteService: GroupInviteService,
              private userService: UserService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.handleVideoDetails(this.searchValue);
    this.eventsSubscription = this.events.subscribe(data => {
      if(this.searchValue != data) {
        this.searchValue = data;
        this.handleVideoDetails(this.searchValue);
      }
    });
  }

  private handleVideoDetails(searchValue: string) {
    this.videoService.findAllPreviews(searchValue, this.pageIndex, this.pageSize)
      .then(res => {
        this.videoFiles = res;
        console.log(this.videoFiles);
        this.isVideoDataLoaded = true;
      })
      .catch(err => {
        this.isVideoDataLoaded = false;
      });
    this.videoService.countAllPreviews(searchValue)
      .subscribe(data => {
        this.totalVideos = data;
      });
  }

  deleteVideoFile($event: any, i: number) {
    if ($event == 'delete') {
      this.videoFiles.splice(i, 1);
    }
  }

  ngOnDestroy() {
    this.eventsSubscription.unsubscribe();
  }

  getPaginatorData(event){
    if(event.pageIndex === this.pageIndex + 1){
      this.lowValue = this.lowValue + this.pageSize;
      this.highValue =  this.highValue + this.pageSize;
      this.pageIndex = event.pageIndex;
      this.handleVideoDetails(this.searchValue);
    }
    else if(event.pageIndex === this.pageIndex - 1){
      this.lowValue = this.lowValue - this.pageSize;
      this.highValue =  this.highValue - this.pageSize;
      this.pageIndex = event.pageIndex;
      this.handleVideoDetails(this.searchValue);
    }
    if(this.pageSize != event.pageSize) {
      this.pageSize = event.pageSize;
      this.handleVideoDetails(this.searchValue);
    }
  }
}
