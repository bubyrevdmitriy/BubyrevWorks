import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const FRIEND_API = 'http://localhost:8080/api/friend/';

@Injectable({
  providedIn: 'root'
})
export class FriendService {

  constructor(private http: HttpClient) { }

  getUserFriendInviteForUser(id: number): Observable<any> {
    return this.http.get(FRIEND_API + 'friendInvite/user/all/' + id);
  }
  getUserFriendInviteSendForUser(id: number): Observable<any> {
    return this.http.get(FRIEND_API + 'friendInvite/user/send/' + id);
  }
  getAllUserFriendInviteReceivedForUser(id: number): Observable<any> {
    return this.http.get(FRIEND_API + 'friendInvite/user/received/' + id);
  }
  getUserPairsForUser(id: number): Observable<any> {
    return this.http.get(FRIEND_API + 'friendPair/user/all/' + id);
  }

  inviteUserToMyFriend(id: number): Observable<any> {
    return this.http.post(FRIEND_API + 'friendInvite/create/' + id, null);
  }
  denyInvitation(id: number): Observable<any> {
    return this.http.delete(FRIEND_API + 'friendInvite/deny/' + id);
  }
  acceptInvitation(id: number): Observable<any> {
    return this.http.post(FRIEND_API + 'friendInvite/accept/' + id, null);
  }
  endFriendship(id: number): Observable<any> {
    return this.http.delete(FRIEND_API + 'friendPair/delete/' + id);
  }


}
