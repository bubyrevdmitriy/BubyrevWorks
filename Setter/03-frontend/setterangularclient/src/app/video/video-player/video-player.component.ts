import {Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {VideoFile} from "../../models/video-file";
import {HttpClient} from "@angular/common/http";
import {VideoService} from "../../service/video.service";
import {LikeService} from "../../service/like.service";
import {CommentService} from "../../service/comment.service";
import {TokenStorageService} from "../../service/token-storage.service";
import {ActivatedRoute} from "@angular/router";
import {UserAuthor} from "../../models/user-author";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {UserLikedListMatDialogComponent} from "../../common/user-liked-list-mat-dialog/user-liked-list-mat-dialog.component";

@Component({
  selector: 'app-video-player',
  templateUrl: './video-player.component.html',
  styleUrls: ['./video-player.component.css']
})
export class VideoPlayerComponent implements OnInit {

  @ViewChild('videoPlayer') videoPlayerRef!: ElementRef;

  @Input()
  videoFile: VideoFile;
  @Input()
  currentPageHostCategory: string;
  @Input()
  currentPageId: number;

  comments: Comment[];
  isCommentDataLoaded: boolean = false;

  constructor(private http: HttpClient,
              private dataService: VideoService) { }

  public videoMetadata: VideoFile  = new VideoFile(0, '', '', '', '');

  ngOnInit(): void {
    this.dataService.findById(this.videoFile.id)
      .then((vmd) => {
        this.videoMetadata = vmd;
        const videoPlayer = this.videoPlayerRef.nativeElement;
        videoPlayer.load();

        const currentTime = sessionStorage.getItem('currentTime');
        if (currentTime) {
          videoPlayer.currentTime = currentTime;
        }

        videoPlayer.ontimeupdate = () => {
          sessionStorage.setItem('currentTime', videoPlayer.currentTime);
        };
      });
  }
}
