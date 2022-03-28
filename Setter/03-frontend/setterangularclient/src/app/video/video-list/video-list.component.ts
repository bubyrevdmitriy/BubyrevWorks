import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {User} from "../../models/user";
import {UserService} from "../../service/user.service";
import {GroupInviteService} from "../../service/group-invite.service";
import {VideoFile} from "../../models/video-file";
import {VideoService} from "../../service/video.service";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-video-list',
  templateUrl: './video-list.component.html',
  styleUrls: ['./video-list.component.css']
})
export class VideoListComponent implements OnInit {

  currentPageHostCategory: string;
  currentPageId: number;
  canUploadNewVideo: boolean = false;

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
    this.route.paramMap.subscribe( () => {
      this.handleVideoDetails();
    })
  }

  private handleVideoDetails() {
    this.currentPageHostCategory = this.route.snapshot.paramMap.get('hostCategory');
    this.currentPageId = +this.route.snapshot.paramMap.get('id');
    if(this.currentPageHostCategory == 'users') {
      this.videoService.findAllPreviewsFromUser(this.currentPageId, this.pageIndex, this.pageSize)
        .then(res => {
          this.videoFiles = res;
          this.isVideoDataLoaded = true;
        })
        .catch(err => {
          this.isVideoDataLoaded = false;
        });
      this.videoService.countPreviewsForUser(this.currentPageId)
        .subscribe(data => {
          this.totalVideos = data;
        });
    }
    if(this.currentPageHostCategory == 'groups') {
      this.videoService.findAllPreviewsFromGroup(this.currentPageId, this.pageIndex, this.pageSize)
        .then(res => {
          this.videoFiles = res;
          this.isVideoDataLoaded = true;
        })
        .catch(err => {
          this.isVideoDataLoaded = false;
        });
      this.videoService.countPreviewsForUserGroup(this.currentPageId)
        .subscribe(data => {
          this.totalVideos = data;
        });
    }
    this.userService.getCurrentUser()
      .subscribe(data => {
        this.loginUser = data;
        if(this.currentPageHostCategory == 'users' && this.loginUser.id == this.currentPageId) {
          this.isUserDataLoaded = true;
          this.canUploadNewVideo = true;
        }
        if(this.currentPageHostCategory == 'groups') {
          this.groupInviteService.isAdmin(this.currentPageId)
            .subscribe(() => {
              this.isUserDataLoaded = true;
              this.canUploadNewVideo = true;
            });
        }
      });
  }



  /*getImage(id: string): Observable<Blob> {
    console.log('this.http.get(id, {responseType: blob})');
    console.log(this.http.get(id, {responseType: "blob"}));
    return this.http.get(id, {responseType: "blob"});
  }*/

  deleteVideoFile($event: any, i: number) {
    if ($event == 'delete') {
      this.videoFiles.splice(i, 1);
    }
  }

  uploadVideoFile($event: string) {
    this.tabIndex = 0;
    this.ngOnInit();
  }

  getPaginatorData(event){
    console.log(event);
    if(event.pageIndex === this.pageIndex + 1){
      this.lowValue = this.lowValue + this.pageSize;
      this.highValue =  this.highValue + this.pageSize;
      this.pageIndex = event.pageIndex;
      this.handleVideoDetails();
    }
    else if(event.pageIndex === this.pageIndex - 1){
      this.lowValue = this.lowValue - this.pageSize;
      this.highValue =  this.highValue - this.pageSize;
      this.pageIndex = event.pageIndex;
      this.handleVideoDetails();
    }
    if(this.pageSize != event.pageSize) {
      this.pageSize = event.pageSize;
      this.handleVideoDetails();
    }
  }
}
