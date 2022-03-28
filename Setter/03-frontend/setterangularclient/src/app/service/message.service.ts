import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Message} from "../models/message";

const MESSAGE_API = 'http://localhost:8080/messages/';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(private http: HttpClient) { }



  createMessage(message: Message): Observable<any> {
    console.log(message);
    return this.http.post(MESSAGE_API + 'create', message);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(MESSAGE_API + id);
  }
}
