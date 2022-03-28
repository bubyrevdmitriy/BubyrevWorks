import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const AUTH_API = 'http://localhost:8080/api/auth/';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  public login(user: { email: string; password: string; }): Observable<any> {
    return this.http.post(AUTH_API + 'signin', {
      email: user.email,
      password: user.password
    });
  }

  public register(user: { firstName: any; lastName: any; password: any; confirmPassword: any; bornDate: any; email: any }): Observable<any> {
    return this.http.post(AUTH_API + 'signup', {
      email: user.email,
      firstName: user.firstName,
      lastName: user.lastName,
      bornDate: user.bornDate,
      password: user.password,
      confirmPassword: user.confirmPassword
    });
  }

  public activateUser(code: string): Observable<any> {
    return this.http.get(AUTH_API + 'activateUser/' + code);
  }

  public sendPasswordRestoreLetter(email: string): Observable<any> {
    return this.http.post(AUTH_API + 'sendPasswordRestoreLetter', email);
  }
  //sendPasswordRestoreLetter
  public checkUserPasswordRestoreCode(code: string): Observable<any> {
    return this.http.get(AUTH_API + 'createNewPassword/' + code);
  }

  public changeUserPassword(code: string,user: {password: any; confirmPassword: any}): Observable<any> {
    return this.http.post(AUTH_API + 'createNewPassword/' + code, {
      password: user.password,
      confirmPassword: user.confirmPassword
    });
  }


}
