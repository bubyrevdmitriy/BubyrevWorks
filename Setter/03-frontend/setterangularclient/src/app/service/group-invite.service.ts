import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const GROUP_API = 'http://localhost:8080/api/group/';

@Injectable({
  providedIn: 'root'
})
export class GroupInviteService {

  constructor(private http: HttpClient) { }

  getUserFriendInviteSendForUser (userId: number): Observable<any> {
    return this.http.get(GROUP_API + 'groupInvite/user/' + userId + '/send');
  }
  getUserFriendInviteReceivedForUser (userId: number): Observable<any> {
    return this.http.get(GROUP_API + 'groupInvite/user/' + userId + '/received');
  }
  getUserPairsForUser (userId: number): Observable<any> {
    return this.http.get(GROUP_API + 'groupPair/user/' + userId + '/all');
  }
  getUserInvitesForUserGroup (userGroupId: number): Observable<any> {
    return this.http.get(GROUP_API + 'groupInvite/userGroup/' + userGroupId + '/all');
  }
  getUserPairsForUserGroup (userGroupId: number): Observable<any> {
    return this.http.get(GROUP_API + 'groupPair/userGroup/' + userGroupId + '/all');
  }
  getUserPairAdminsForUserGroup (userGroupId: number): Observable<any> {
    return this.http.get(GROUP_API + 'groupPair/userGroup/' + userGroupId + '/admin');
  }
  getUserGroupAvailableToInviteForUser (userId: number): Observable<any> {
    return this.http.get(GROUP_API + 'groupInvite/user/' + userId + '/availableToInvite');
  }
  inviteUserToUserGroup (userGroupId: number, userId: number): Observable<any> {
    return this.http.post(GROUP_API + 'groupInvite/' + userGroupId + '/create/' + userId, null);
  }


  denyAllUserGroupInvitation (userGroupId: number): Observable<any> {
    return this.http.delete(GROUP_API + 'groupInvite/' + userGroupId + '/deny');
  }
  denyUserGroupInvitation (userGroupId: number, userId: number): Observable<any> {
    return this.http.delete(GROUP_API + 'groupInvite/' + userGroupId + '/deny/' + userId);
  }
  acceptAllUserGroupInvitation (userGroupId: number): Observable<any> {
    return this.http.patch(GROUP_API + 'groupInvite/' + userGroupId + '/accept', null);
  }
  acceptUserGroupInvitation (userGroupId: number, userId: number): Observable<any> {
    return this.http.patch(GROUP_API + 'groupInvite/' + userGroupId + '/accept/' + userId, null);
  }




  enterToGroup(userGroupId: number): Observable<any> {
    return this.http.post(GROUP_API + 'enterToGroup/' + userGroupId, null);
  }
  endGroupMembership(userGroupId: number): Observable<any> {
    return this.http.delete(GROUP_API + 'groupPair/' + userGroupId + '/delete');
  }

  kickUserFromUserGroup(userGroupId: number, userId: number): Observable<any> {
    return this.http.patch(GROUP_API + 'groupPair/' + userGroupId + '/kickUserFromUserGroup/' + userId, null);
  }

  makeNewAdmin(userGroupId: number, userId: number): Observable<any> {
    return this.http.patch(GROUP_API + 'groupPair/' + userGroupId + '/makeAdmin/' + userId, null);
  }
  endAdmin(userGroupId: number): Observable<any> {
    return this.http.patch(GROUP_API + 'groupPair/' + userGroupId + '/endAdmin', null);
  }
  isAdmin(userGroupId: number): Observable<any> {
    return this.http.get(GROUP_API + 'groupPair/' + userGroupId + '/isAdmin');
  }
}
