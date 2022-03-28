import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AudioFile} from "../models/audio-file";

const USER_API = 'http://localhost:8080/api/user/';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }


  public getUserById(id: number): Observable<any> {
    return this.http.get(USER_API + id);
  }

  public getCurrentUser(): Observable<any> {
    return this.http.get(USER_API);
  }

  public updateUser(user: any): Observable<any> {
    return this.http.patch(USER_API + 'update', user);
  }


  getAllUsers(lastNameSearch: string, thePage: number, thePageSize:  number): Observable<any> {
    if(lastNameSearch == null) {
      return this.http.get(USER_API + 'all?page=' + thePage + '&size=' + thePageSize);
    } else {
      return this.http.get(USER_API + 'all?page=' + thePage + '&size=' + thePageSize + '&lastNameSearch=' + lastNameSearch);
    }
  }
  countAllUsers(lastNameSearch: string): Observable<any> {
    if(lastNameSearch == null) {
      return this.http.get(USER_API + 'count/all')
    } else {
      return this.http.get(USER_API + 'count/all' + '?lastNameSearch=' + lastNameSearch)
    }
  }
}
