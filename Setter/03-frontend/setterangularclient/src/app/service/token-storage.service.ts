import { Injectable } from '@angular/core';
import {User} from "../models/user";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CookieService} from "ngx-cookie-service";

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';
const AUTH_API = 'http://localhost:8080/api/auth/';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor(private cookie: CookieService,
              private http: HttpClient) { }

  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token)

    //URLEncoder.encode(token, "UTF-8");
    //this.deleteAllCookies();
    this.cookie.delete(TOKEN_KEY);
    this.cookie.set(TOKEN_KEY, token)
  }

  public getToken(): string | null {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public getTokenCookie(): string | null {
    return this.cookie.get(TOKEN_KEY);
  }

  public saveUser(user: User): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));

    /*this.cookie.delete(USER_KEY);
    this.cookie.set(USER_KEY, JSON.stringify(user));*/
  }

  public getUser(): any {
    return JSON.parse(<string>sessionStorage.getItem(USER_KEY));
  }

  logOut() {
    window.sessionStorage.clear();
    this.cookie.delete(TOKEN_KEY);
    this.deleteAllCookies()
    window.location.reload();
  }

  /*window.onbeforeunload = function (e) {
    deleteAllCookies();
  }*/

  deleteAllCookies() {
    /*for (let i = 0; i < 10; i++) {
      this.cookie.delete(TOKEN_KEY);
    }
    document.cookie="name="+$scope.user.name+";expires="+now.toGMTString()+";path=/";

document.cookie = "name=; expires=Thu, 18 Dec 2013 12:00:00 GMT; path=/";

    */
    let cookies = document.cookie.split(";");

    for (let i = 0; i < cookies.length; i++) {
      let cookie = cookies[i];
      let eqPos = cookie.indexOf("=");
      let name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
      document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
    }
  }
}
