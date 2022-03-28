import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Group} from "../models/group";
import {AudioFile} from "../models/audio-file";

const GROUP_API = 'http://localhost:8080/api/group/';

@Injectable({
  providedIn: 'root'
})
export class GroupService {

  constructor(private http: HttpClient) { }

  createUserGroup (group: Group): Observable<any> {
    return this.http.post(GROUP_API + 'create', group);
  }







  getAllUserGroups (nameSearch: string, thePage: number, thePageSize:  number): Observable<any> {
    if(nameSearch == null) {//nameSearch.length>0
      return this.http.get(GROUP_API + 'all?page=' + thePage + '&size=' + thePageSize);
    } else {
      return this.http.get(GROUP_API + 'all?page=' + thePage + '&size=' + thePageSize + '&nameSearch=' + nameSearch);
    }
  }
  countAllUserGroups(descriptionSearch: string): Observable<any> {
    if(descriptionSearch == null) {
      return this.http.get(GROUP_API + 'count/all')
    } else {
      return this.http.get(GROUP_API + 'count/all' + '?descriptionSearch=' + descriptionSearch)
    }
  }
















  getUserGroup (userGroupId: number): Observable<any> {
    return this.http.get(GROUP_API + userGroupId );
  }
  updateUserGroup (group: Group, userGroupId: number): Observable<any> {
    return this.http.patch(GROUP_API + userGroupId, group);
  }
  deleteUserGroup (userGroupId: number): Observable<any> {
    return this.http.delete(GROUP_API + userGroupId);
  }
}
