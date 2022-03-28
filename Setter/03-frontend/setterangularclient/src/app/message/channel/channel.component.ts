import {Component, ElementRef, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../service/user.service";
import {ChannelService} from "../../service/channel.service";
import {User} from "../../models/user";
import {UserAuthor} from "../../models/user-author";
import {Channel} from "../../models/channel";
import {Message} from "../../models/message";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MessageService} from "../../service/message.service";
import {NotificationService} from "../../service/notification.service";
import { Stomp } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import {TokenStorageService} from "../../service/token-storage.service";

const url = 'http://localhost:8080';

@Component({
  selector: 'app-channel',
  templateUrl: './channel.component.html',
  styleUrls: ['./channel.component.css']
})
export class ChannelComponent implements OnInit, OnDestroy {

  TOKEN_HEADER_KEY = 'Authorization';

  isAvailableChannel: boolean = false;
  currentUserId: number;
  isCurrentUserIdLoaded = false;
  currentUser: UserAuthor = new UserAuthor();
  isCurrentUserLoaded = false;
  otherUser: UserAuthor = new UserAuthor();
  isOtherUserLoaded = false;
  channel: Channel;
  isChannelDataLoaded = false;
  messages: Message[];
  isMessagesDataLoaded = false;
  public messageFormGroup: FormGroup;
  stompClient;
  currentChannelId

 @ViewChild("messageContainer") mContainer: ElementRef;

  constructor(private userService: UserService,
              private messageService: MessageService,
              private channelService: ChannelService,
              private notificationService: NotificationService,
              private fb: FormBuilder,
              private tokenService: TokenStorageService,
              private route: ActivatedRoute
  ) { }

  ngOnDestroy(): void {
        this.disconnect();
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe( () => {
      this.handleUserDetails();
    });
    this.messageFormGroup = this.createMessageForm();

  }

  private connectToChat(chatName: number) {
    let socket = new SockJS(url + '/chat');//как в java config
    this.stompClient = Stomp.over(socket);
    const _this = this;

    const token = this.tokenService.getToken();

    var header = {
      'Authorization' : token
    }

    // @ts-ignore
    this.stompClient.connect(header, function(frame) {
      // @ts-ignore
      _this.stompClient.subscribe("/topic/messages/" + chatName , function (response) {
        let data = JSON.parse(response.body);
        _this.showMessages(data);
      });
    });
  }

  showMessages(data: any){
    let newMessage: Message = new Message();
    newMessage.id=data.id;
    newMessage.senderId=data.senderId;
    newMessage.recipientId=data.recipientId;
    newMessage.content=data.content;

    let newDate = new Date(data.createdDate);
    let dateArray= [];
    dateArray.push(newDate.getFullYear())
    dateArray.push(newDate.getMonth())
    dateArray.push(newDate.getDay())
    dateArray.push(newDate.getHours())
    dateArray.push(newDate.getMinutes())
    dateArray.push(newDate.getSeconds())
    dateArray.push(newDate.getMilliseconds())

    // @ts-ignore
    newMessage.createdDate=dateArray;
    newMessage.channelId=data.channelId;
    newMessage.status=data.status;
    this.messages.push(newMessage);
    //this.mContainer.nativeElement.scrollTop = this.mContainer.nativeElement.scrollHeight;
    this.scrollToBottom();
  }

  disconnect() {
    if (this.stompClient != null) {
      this.stompClient.disconnect();
    }
    console.log('Disconnected!');
  }

  ngAfterViewChecked() {
    this.scrollToBottom();
  }

  scrollToBottom(): void {
    try {
      this.mContainer.nativeElement.scrollTop = this.mContainer.nativeElement.scrollHeight;
      //this.myScrollContainer.nativeElement.scrollTop = this.myScrollContainer.nativeElement.scrollHeight;
    } catch(err) { }
  }

  private handleUserDetails() {
    this.currentChannelId = +this.route.snapshot.paramMap.get('id');
    this.connectToChat(this.currentChannelId);
    this.channelService.getAllMessagesForChannel(this.currentChannelId).subscribe(
      data => {
        if(data != null) {
          this.messages = data;
          this.isMessagesDataLoaded = true;

          this.isAvailableChannel = true;

          this.userService.getCurrentUser()
            .subscribe(data => {
              this.currentUserId = data.id;
              this.isCurrentUserIdLoaded = true;

              //getChannelShortInfo
              this.channelService.getChannelShortInfo(this.currentChannelId)
                .subscribe(data => {
                  this.channel = data;
                  this.isChannelDataLoaded = true;

                  if (this.channel.recipientUser.id == this.currentUserId) {
                    this.currentUser = this.channel.recipientUser;
                    this.isCurrentUserLoaded = true;

                    this.otherUser = this.channel.senderUser;
                    this.isOtherUserLoaded = true;
                  } else {
                    this.currentUser = this.channel.senderUser;
                    this.isCurrentUserLoaded = true;

                    this.otherUser = this.channel.recipientUser;
                    this.isOtherUserLoaded = true;
                  }
                });
            });
        } else {
          this.isAvailableChannel = false;
          this.notificationService.showSnackBar('Channel is not available!');
        }
    }, error => {
        this.notificationService.showSnackBar('Channel is not available!');
    })
  }

  private createMessageForm() {
    return this.fb.group({
      content: ['', Validators.compose([Validators.required, Validators.maxLength(250)])]
    });
  }

  submit() {
    let newMessage: Message = new Message();
    newMessage.senderId = this.currentUser.id;
    newMessage.recipientId = this.otherUser.id;
    newMessage.content = this.messageFormGroup.value.content;
    newMessage.channelId =this.channel.id;
    this.stompClient.send("/app/chat/" + this.currentChannelId, {},JSON.stringify({
      senderId: this.currentUser.id,
      recipientId: this.otherUser.id,
      content: this.messageFormGroup.value.content,
      channelId: this.channel.id
    }));
    this.messageFormGroup.reset();
  }

  /*deleteMessage(id: number, i: number) {
    this.messageService.delete(id).subscribe(data => {
      this.notificationService.showSnackBar(data);
      this.messages.splice(i, 1);
    }, error => {
      this.notificationService.showSnackBar(error);
      this.ngOnInit();
    })
  }*/
}
