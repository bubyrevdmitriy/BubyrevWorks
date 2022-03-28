import {AfterViewChecked, Component, ElementRef, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {User} from "../../models/user";
import {UserService} from "../../service/user.service";
import {GroupInviteService} from "../../service/group-invite.service";
import {AudioFile} from "../../models/audio-file";
import {AudioService} from "../../service/audio.service";
import {LikeService} from "../../service/like.service";
import {Subject} from "rxjs";

@Component({
  selector: 'app-audio-list',
  templateUrl: './audio-list.component.html',
  styleUrls: ['./audio-list.component.css']
})
export class AudioListComponent implements OnInit, AfterViewChecked, OnDestroy {

  public innerWidth: number;
  public audioPlayerWidth: number = 500;
  tabIndex = 0;

  currentPageHostCategory: string;
  currentPageId: number;
  canUploadNewAudio: boolean = false;

  loginUser: User = new User();
  isUserDataLoaded: boolean = false;

  audioFileArray: AudioFile[];
  isAudioFileArrayDataLoaded: boolean = false;

  public audioMetadata: AudioFile  = new AudioFile(0, '', '', '', '');
  isAudioLoaded: boolean = false;

  playingAudio: number = null;
  isAudioStopped: boolean = false;
  isRequestFromSingleAudio = false;

  @ViewChild('audioPlayer') audioPlayerRef!: ElementRef;
  audioPlayer;

  eventsSubject: Subject<string> = new Subject<string>();

  // properties for pagination
  pageIndex:number = 0;
  pageSize:number = 9;
  lowValue:number = 0;
  highValue:number = 50;

  totalAudios;

  constructor(private groupInviteService: GroupInviteService,
              private userService: UserService,
              private audioService: AudioService,
              private likeService: LikeService,
              private route: ActivatedRoute) { }

  ngAfterViewChecked(): void {
    this.audioPlayer = this.audioPlayerRef.nativeElement;
  }

  ngOnDestroy(): void {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe( () => {
      this.handleAudioDetails();
    })
  }


  private handleAudioDetails() {
    this.currentPageHostCategory = this.route.snapshot.paramMap.get('hostCategory');
    this.currentPageId = +this.route.snapshot.paramMap.get('id');
    if(this.currentPageHostCategory == 'users') {
      this.audioService.findAllAudiosFromUser(this.currentPageId, this.pageIndex, this.pageSize)
        .then(res => {
          this.audioFileArray = res;
          this.isAudioFileArrayDataLoaded = true;

        })
        .catch(err => {
          this.isAudioFileArrayDataLoaded = false;
        });
      this.audioService.countAllAudiosForUser(this.currentPageId)
        .subscribe(data => {
          this.totalAudios = data;
        });
    }
    if(this.currentPageHostCategory == 'groups') {
      this.audioService.findAllAudiosFromGroup(this.currentPageId, this.pageIndex, this.pageSize)
        .then(res => {
          this.audioFileArray = res;
          this.isAudioFileArrayDataLoaded = true;
        })
        .catch(err => {
          this.isAudioFileArrayDataLoaded = false;
        });
      this.audioService.countAllAudiosForUserGroup(this.currentPageId)
        .subscribe(data => {
          this.totalAudios = data;
        });
    }
    this.userService.getCurrentUser()
      .subscribe(data => {
        this.loginUser = data;

        if(this.currentPageHostCategory == 'users' && this.loginUser.id == this.currentPageId) {
          this.isUserDataLoaded = true;
          this.canUploadNewAudio = true;
        }
        if(this.currentPageHostCategory == 'groups') {
          this.groupInviteService.isAdmin(this.currentPageId)
            .subscribe(() => {
              this.canUploadNewAudio = true;
              this.isUserDataLoaded = true;
            });
        }
      });
  }

  playFile (i: number) {
    this.audioService.findById(this.audioFileArray[i].id)
      .then((vmd) => {

        this.audioFileArray[i].isLoaded = true;
        this.playingAudio = i;

        this.audioMetadata = vmd;
        //const audioPlayer = this.audioPlayerRef.nativeElement;
        this.audioPlayer.load();

        /*const currentTime = sessionStorage.getItem('currentTime');
        if (currentTime) {
          this.audioPlayer.currentTime = currentTime;
        }*/

        this.audioPlayer.ontimeupdate = () => {
          sessionStorage.setItem('currentTime', this.audioPlayer.currentTime);
        };

        this.audioPlayer.play();
      });
  }


  likeAudioFile(i: number) {
    this.likeService.likeAudioFile(this.audioFileArray[i].id).subscribe(data => {
      this.audioFileArray[i].likedByLoginUser = true;
      this.audioFileArray[i].likes = this.audioFileArray[i].likes + 1;
    }, error => {
    })
  }

  disLikeAudioFile(i: number) {
    this.likeService.dislikeAudioFile(this.audioFileArray[i].id).subscribe(data => {
      this.audioFileArray[i].likedByLoginUser = false;
      this.audioFileArray[i].likes = this.audioFileArray[i].likes - 1;
    }, error => {
    })
  }

  deleteAudioFile($event: any, i: number) {
    if ($event == 'delete') {
      this.audioFileArray.splice(i, 1);
    }
  }

  uploadAudioFile($event: string) {
    this.tabIndex = 0;
    this.ngOnInit();
  }

  getPaginatorData(event){
    if(event.pageIndex === this.pageIndex + 1){
      this.lowValue = this.lowValue + this.pageSize;
      this.highValue =  this.highValue + this.pageSize;
      this.pageIndex = event.pageIndex;
      this.handleAudioDetails();
    }
    else if(event.pageIndex === this.pageIndex - 1){
      this.lowValue = this.lowValue - this.pageSize;
      this.highValue =  this.highValue - this.pageSize;
      this.pageIndex = event.pageIndex;
      this.handleAudioDetails();
    }
    if(this.pageSize != event.pageSize) {
      this.pageSize = event.pageSize;
      this.handleAudioDetails();
    }
  }

  audioEnded() {
    if (this.playingAudio != this.audioFileArray.length-1) {
      this.playRequestHandle('load',this.playingAudio +1);
    } else {
      this.playRequestHandle('load',0);
    }
  }

  audioPaused() {
    if (this.isRequestFromSingleAudio == true) {
      this.isRequestFromSingleAudio = false;
    } else {
      this.eventsSubject.next('pause');
    }
  }

  audioPlayed() {
    if (this.isRequestFromSingleAudio == true) {
      this.isRequestFromSingleAudio = false;
    } else {
      this.eventsSubject.next('play');
    }
  }

  playRequestHandle($event: any, i: number) {
    if ($event == 'load') {
      if (this.playingAudio != null) {
        this.eventsSubject.next('end');
        this.audioFileArray[this.playingAudio].isLoaded = false;
      }
      this.playFile(i);
    }
    if ($event == 'pause') {
      this.isRequestFromSingleAudio = true;
      this.audioPlayer.pause();
    }
    if ($event == 'play') {
      this.isRequestFromSingleAudio = true;
      this.audioPlayer.play();
    }
  }
}
