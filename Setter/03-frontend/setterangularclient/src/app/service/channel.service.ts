import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Post} from "../models/post";

const CHANNEL_API = 'http://localhost:8080/api/channel/';

@Injectable({
  providedIn: 'root'
})
export class ChannelService {

  constructor(private http: HttpClient) { }

  createChannel(id: number): Observable<any> {
    return this.http.post(CHANNEL_API + 'create/' + id, null);
  }

  getAllChannelsForUser(): Observable<any> {
    return this.http.get(CHANNEL_API + 'myChannels');
  }

  getChannelShortInfo(id: number): Observable<any> {
    return this.http.get(CHANNEL_API + 'shortInfo/' + id);
  }

  /*findMessages(id: number): Observable<any> {
    return this.http.get(CHANNEL_API + id);
  }*/

  getAllMessagesForChannel(id: number): Observable<any> {
    return this.http.get(CHANNEL_API + id );
  }

  delete(id: number): Observable<any> {
    return this.http.delete(CHANNEL_API + id);
  }

}
