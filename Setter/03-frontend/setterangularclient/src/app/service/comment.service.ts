import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const COMMENT_API = 'http://localhost:8080/api/comment/';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) { }

  addCommentToPost(postId: number, text: String): Observable<any> {
    return this.http.post(COMMENT_API + 'posts/' + postId + '/create', {
      text: text
    });
  }

  addCommentToCommonImage(commonImageId: number, text: String): Observable<any> {
    return this.http.post(COMMENT_API + 'commonImages/' + commonImageId + '/create', {
      text: text
    });
  }

  addCommentToVideoFile(videoFileId: number, text: String): Observable<any> {
    return this.http.post(COMMENT_API + 'videoFiles/' + videoFileId + '/create', {
      text: text
    });
  }

  getCommentsToPost(postId: number): Observable<any> {
    return this.http.get(COMMENT_API + 'posts/' + postId + '/all');
  }

  getCommentsToCommonImage(commonImageId: number): Observable<any> {
    return this.http.get(COMMENT_API + 'commonImages/' + commonImageId + '/all');
  }

  getCommentsToVideoFile(videoFileId: number): Observable<any> {
    return this.http.get(COMMENT_API + 'videoFiles/' + videoFileId + '/all');
  }

  delete(commentId: number): Observable<any> {
    return this.http.delete(COMMENT_API + commentId);
  }

}
