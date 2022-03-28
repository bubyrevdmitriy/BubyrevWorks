import {AfterViewChecked, Component, ElementRef, Input, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {User} from "../../models/user";
import {AudioFile} from "../../models/audio-file";
import {GroupInviteService} from "../../service/group-invite.service";
import {UserService} from "../../service/user.service";
import {AudioService} from "../../service/audio.service";
import {LikeService} from "../../service/like.service";
import {ActivatedRoute} from "@angular/router";
import {Observable, Subscription} from "rxjs";

@Component({
  selector: 'app-search-audio-list',
  templateUrl: './search-audio-list.component.html',
  styleUrls: ['./search-audio-list.component.css']
})
export class SearchAudioListComponent implements OnInit, AfterViewChecked, OnDestroy {

  //@Input() searchValue: string;
  @Input() events: Observable<string>;
  searchValue: string;
  private eventsSubscription: Subscription;
  loginUser: User = new User();
  isUserDataLoaded: boolean = false;

  audioFileArray: AudioFile[];
  isAudioFileArrayDataLoaded: boolean = false;

  public audioMetadata: AudioFile  = new AudioFile(0, '', '', '', '');
  isVideoLoaded: boolean = false;

  playingAudio: number = null;

  @ViewChild('audioPlayer') audioPlayerRef!: ElementRef;
  audioPlayer;

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

  ngOnInit(): void {
    this.handleAudioDetails(this.searchValue);
    this.eventsSubscription = this.events.subscribe(data => {
      if(this.searchValue != data) {
        this.searchValue = data;
        this.handleAudioDetails(this.searchValue);
      }
    });
  }

  ngAfterViewChecked(): void {
    this.audioPlayer = this.audioPlayerRef.nativeElement;
  }

  ngOnDestroy() {
    this.eventsSubscription.unsubscribe();

  }

  private handleAudioDetails(searchValue: string) {
    this.audioService.findAll(searchValue, this.pageIndex, this.pageSize)
      .then(res => {
        this.audioFileArray = res;
        this.isAudioFileArrayDataLoaded = true;
      })
      .catch(err => {
        this.isAudioFileArrayDataLoaded = false;
      });
    this.audioService.countAll(searchValue)
      .subscribe(data => {
        this.totalAudios = data;
      });
  }

  playArray(i: number) {
    if (this.playingAudio != null) {
      this.audioFileArray[this.playingAudio].isLoaded = false;
    }

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
    console.log('deleteAudioFile($event: any, i: number) {')
    if ($event == 'delete') {

      this.audioFileArray.splice(i, 1);
    }
  }

  getPaginatorData(event){
    console.log(event);
    if(event.pageIndex === this.pageIndex + 1){
      this.lowValue = this.lowValue + this.pageSize;
      this.highValue =  this.highValue + this.pageSize;
      this.pageIndex = event.pageIndex;
      this.handleAudioDetails(this.searchValue);
    }
    else if(event.pageIndex === this.pageIndex - 1){
      this.lowValue = this.lowValue - this.pageSize;
      this.highValue =  this.highValue - this.pageSize;
      this.pageIndex = event.pageIndex;
      this.handleAudioDetails(this.searchValue);
    }
    if(this.pageSize != event.pageSize) {
      this.pageSize = event.pageSize;
      this.handleAudioDetails(this.searchValue);
    }
  }

  audioEnded() {
    if (this.playingAudio != this.audioFileArray.length-1) {
      this.playArray(this.playingAudio +1);
    } else {
      this.playArray(0);
    }
  }
}
