import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AudioFile} from "../../models/audio-file";
import {GroupInviteService} from "../../service/group-invite.service";
import {UserService} from "../../service/user.service";
import {AudioService} from "../../service/audio.service";
import {LikeService} from "../../service/like.service";
import {UserAuthor} from "../../models/user-author";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {UserLikedListMatDialogComponent} from "../../common/user-liked-list-mat-dialog/user-liked-list-mat-dialog.component";
import {NotificationService} from "../../service/notification.service";
import {Observable, Subscription} from "rxjs";

@Component({
  selector: 'app-single-audio',
  templateUrl: './single-audio.component.html',
  styleUrls: ['./single-audio.component.css']
})
export class SingleAudioComponent implements OnInit {

  @Input()
  audioFile: AudioFile;

  @Input()
  events: Observable<string>;
  private eventsSubscription: Subscription;

  @Output()
  deleteRequest = new EventEmitter<string>();

  @Output()
  playRequest = new EventEmitter<string>();

  constructor(private groupInviteService: GroupInviteService,
              private userService: UserService,
              private audioService: AudioService,
              private likeService: LikeService,
              private dialog: MatDialog,
              private notificationService: NotificationService) { }

  ngOnInit(): void {
    this.audioFile.isLoaded = false;
    this.audioFile.isStopped = false;
    this.eventsSubscription = this.events.subscribe(data => {
      if(this.audioFile.isLoaded == true) {
        this.handleAudioEvents(data);
      }
    });
  }

  likeAudioFile() {
    this.likeService.likeAudioFile(this.audioFile.id).subscribe(data => {
      this.audioFile.likedByLoginUser = true;
      this.audioFile.likes = this.audioFile.likes + 1;
    }, error => {
    })
  }

  disLikeAudioFile() {
    this.likeService.dislikeAudioFile(this.audioFile.id).subscribe(data => {
      this.audioFile.likedByLoginUser = false;
      this.audioFile.likes = this.audioFile.likes - 1;
    }, error => {
    })
  }

  openUserLikeToAudioFileList() {
    let userLikedList: UserAuthor[];
    this.likeService.getAllLikesForAudioFile(this.audioFile.id).subscribe(data => {
      userLikedList = data;
      const dialogUserInviteConfig = new MatDialogConfig();
      dialogUserInviteConfig.width = '600px';
      dialogUserInviteConfig.data = {
        userLikedList: userLikedList,
        heading: 'User liked audio:'
      };
      this.dialog.open(UserLikedListMatDialogComponent, dialogUserInviteConfig);
    }, error => {
    })
  }

  deleteAudioFile() {
    this.audioService.deleteAudioFile(this.audioFile.id)
      .subscribe(() => {
        this.notificationService.showSnackBar('Audio was successfully deleted!');
        this.deleteRequest.emit('delete');
      }, error => {
        this.notificationService.showSnackBar('Audio was not deleted! Some error occurred');
      });
  }

  play() {
    if (this.audioFile.isLoaded == false && this.audioFile.isStopped == false) {
      //запускаем видео
      this.audioFile.isLoaded = true;
      this.audioFile.isStopped = false;
      this.playRequest.emit('load');
    } else if (this.audioFile.isLoaded == true && this.audioFile.isStopped == false) {
      //ставим на паузу
      this.audioFile.isLoaded = true;
      this.audioFile.isStopped = true;
      this.playRequest.emit('pause');
    } else if (this.audioFile.isLoaded == true && this.audioFile.isStopped == true) {
      //снимаем с паузы
      this.audioFile.isLoaded = true;
      this.audioFile.isStopped = false;
      this.playRequest.emit('play');
    }
  }

  private handleAudioEvents(data: string) {
    if (data == 'pause') {
      this.audioFile.isLoaded = true;
      this.audioFile.isStopped = true;
    }
    if (data == 'play') {
      this.audioFile.isLoaded = true;
      this.audioFile.isStopped = false;
    }
    if (data == 'end') {
      this.audioFile.isLoaded = false;
      this.audioFile.isStopped = false;
    }
  }


}
