import {AfterContentInit, AfterViewInit, Component, OnInit} from '@angular/core';
import {Post} from "../../models/post";
import {CommonImage} from "../../models/common-image";
import {User} from "../../models/user";
import {UserService} from "../../service/user.service";
import {ImageUploadService} from "../../service/image-upload.service";
import {ActivatedRoute} from "@angular/router";
import {CommonImageInAlbum} from "../../models/common-image-in-album";

import {PageEvent} from '@angular/material/paginator';

@Component({
  selector: 'app-photo-list',
  templateUrl: './photo-list.component.html',
  styleUrls: ['./photo-list.component.css']
})
export class PhotoListComponent implements OnInit {

  currentPageHostCategory: string;
  currentPageId: number;
  commonImagesInAlbum: CommonImageInAlbum[];
  isCommonImagesDataLoaded: Boolean = false;

  // properties for pagination
  pageIndex:number = 0;
  pageSize:number = 9;
  lowValue:number = 0;
  highValue:number = 50;

  totalImages;

  constructor(private userService: UserService,
              private imageService:  ImageUploadService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe( () => {
      this.handlePhotoDetails();
    })
  }

  handlePhotoDetails() {
    this.currentPageHostCategory = this.route.snapshot.paramMap.get('hostCategory');
    this.currentPageId = +this.route.snapshot.paramMap.get('id');

    if (this.currentPageHostCategory == 'users') {
      this.imageService.getImagesForUserPaginate(this.currentPageId, this.pageIndex, this.pageSize)
        .subscribe(data => {
          this.commonImagesInAlbum = data;
          this.isCommonImagesDataLoaded = true;
        });
      this.imageService.countImagesForUser(this.currentPageId)
        .subscribe(data => {
          this.totalImages = data;
        });
    }
    if (this.currentPageHostCategory == 'groups') {
      this.imageService.getAllImagesForUserGroupPaginate(this.currentPageId, this.pageIndex, this.pageSize)
        .subscribe(data => {
          this.commonImagesInAlbum = data;
          this.isCommonImagesDataLoaded = true;
        });
      this.imageService.countAllImagesForUserGroup(this.currentPageId)
        .subscribe(data => {
          this.totalImages = data;
        });
    }
  }

  deleteCommonImage($event: string, i: number) {
    if ($event == 'delete') {
      this.commonImagesInAlbum.splice(i, 1);
    }
  }

  getPaginatorData(event){
    console.log(event);
    if(event.pageIndex === this.pageIndex + 1){
      this.lowValue = this.lowValue + this.pageSize;
      this.highValue =  this.highValue + this.pageSize;
      this.pageIndex = event.pageIndex;
      this.handlePhotoDetails();
    }
    else if(event.pageIndex === this.pageIndex - 1){
      this.lowValue = this.lowValue - this.pageSize;
      this.highValue =  this.highValue - this.pageSize;
      this.pageIndex = event.pageIndex;
      this.handlePhotoDetails();
    }
    if(this.pageSize != event.pageSize) {
      this.pageSize = event.pageSize;
      this.handlePhotoDetails();
    }
  }
}
