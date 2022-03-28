import { Component, OnInit } from '@angular/core';
import { Stomp } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import {HttpClient} from "@angular/common/http";
import {Message} from "../message";

/*
# Add sockjs and stompjs dependencies
npm install sockjs-client --save
npm install @stomp/stompjs --save
Then import it into your app:

import SockJS from "sockjs-client"
import Stomp from "@stomp/stompjs"
*/
const url = 'http://localhost:8080';

@Component({
  selector: 'app-chat-component',
  templateUrl: './chat-component.component.html',
  styleUrls: ['./chat-component.component.css']
})

export class ChatComponentComponent implements OnInit {

  // @ts-ignore
  stompClient;
  // @ts-ignore
  selectedChat;
  // @ts-ignore
  fromLogin;
  users: string[] = [];
  chats: string[] = [];
  isUsersDataLoaded = false;
  isChatsDataLoaded = false;
  newMessages: Message[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.fetchAllUsers();
    this.fetchAllChats();
  }

  login(value: string) {
    if(this.users.includes(value)){
      this.fromLogin = value;
    }
  }

  selectChat(chat: any) {
    if (this.stompClient != null) {
      this.disconnect();
    }
    this.selectedChat = chat;
    this.connectToChat(chat);
  }

  registration(userName: string) {
    // @ts-ignore
    if (userName.length > 1) {
      this.http.get(url + '/registration/' + userName).subscribe(
        ()=>{
          this.users.push(userName);
        }, error => {
        });
    }
  }

  fetchAllUsers() {
    this.http.get(url + '/fetchAllUsers').subscribe(
      (data)=>{
        // @ts-ignore
        this.users = data;
        this.isUsersDataLoaded = true;
      });
  }

  send(text: string) {
    //console.log(text);
    if (text.length>1) {
      if (this.selectedChat != null && this.fromLogin != null && this.stompClient != null) {
        console.log(this.fromLogin + " message to: " + this.selectedChat);
        this.stompClient.send("/app/chat/" + this.selectedChat, {}, JSON.stringify({
          fromLogin: this.fromLogin,
          message: text
        }));
      }
    }

  }

  private connectToChat(chatName: string) {
    console.log("connecting to chat...")
    let socket = new SockJS(url + '/chat');//как в java config
    this.stompClient = Stomp.over(socket);
    const _this = this;
    // @ts-ignore
    this.stompClient.connect({}, function(frame) {
      console.log("connected to: " + frame);
      // @ts-ignore
      _this.stompClient.subscribe("/topic/messages/" + chatName , function (response) {
        let data = JSON.parse(response.body);
        _this.showMessages(data.message, data.fromLogin)
      });
    });
  }

  disconnect() {
    if (this.stompClient != null) {
      this.stompClient.disconnect();
    }
    console.log('Disconnected!');
  }

  showMessages(messageValue: any, fromLoginValue: any){
    let message = new Message(fromLoginValue, messageValue)
    this.newMessages.push(message);
    console.log(message);
  }

  createChat(chatName: string) {
    // @ts-ignore
    if (chatName.length > 1) {
      //console.log(url + '/createNewChat/' + chatName);
      this.http.get(url + '/createNewChat/' + chatName).subscribe(
        ()=>{
          this.chats.push(chatName);
        }, error => {
        });
    }
  }

  fetchAllChats() {
    this.http.get(url + '/fetchAllChats').subscribe(
      (data)=>{
        // @ts-ignore
        this.chats = data;
        //console.log(this.users)
        this.isChatsDataLoaded = true;
      });
  }
}
