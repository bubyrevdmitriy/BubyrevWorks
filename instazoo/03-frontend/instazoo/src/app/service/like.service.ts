import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const LIKE_API = 'http://localhost:8080/api/like/';

@Injectable({
  providedIn: 'root'
})
export class LikeService {

  constructor(private http: HttpClient) { }

  getAllLikesForPost(postId: number): Observable<any> {
    return this.http.get(LIKE_API + 'post/' + postId);
  }

  likePost(postId: number): Observable<any> {
    return this.http.post(LIKE_API + 'post/' + postId, null);
  }

  dislikePost(postId: number): Observable<any> {
    return this.http.delete(LIKE_API + 'post/' + postId);
  }
}
