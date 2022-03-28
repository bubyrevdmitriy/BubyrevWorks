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

  getAllLikesForComment(commentId: number): Observable<any> {
    return this.http.get(LIKE_API + 'comment/' + commentId);
  }

  getAllLikesForCommonImage(commonImageId: number): Observable<any> {
    return this.http.get(LIKE_API + 'commonImage/' + commonImageId);
  }

  getAllLikesForAudioFile(audioFileId: number): Observable<any> {
    return this.http.get(LIKE_API + 'audioFile/' + audioFileId);
  }

  getAllLikesForVideoFile(videoFileId: number): Observable<any> {
    return this.http.get(LIKE_API + 'videoFile/' + videoFileId);
  }

  likePost(postId: number): Observable<any> {
    return this.http.post(LIKE_API + 'post/' + postId, null);
  }

  likeComment(commentId: number): Observable<any> {
    return this.http.post(LIKE_API + 'comment/' + commentId, null);
  }

  likeCommonImage(commonImageId: number): Observable<any> {
    return this.http.post(LIKE_API + 'commonImage/' + commonImageId, null);
  }

  likeAudioFile(audioFileId: number): Observable<any> {
    return this.http.post(LIKE_API + 'audioFile/' + audioFileId, null);
  }

  likeVideoFile(videoFileId: number): Observable<any> {
    return this.http.post(LIKE_API + 'videoFile/' + videoFileId, null);
  }

  dislikePost(postId: number): Observable<any> {
    return this.http.delete(LIKE_API + 'post/' + postId);
  }

  disLikeComment(commentId: number): Observable<any> {
    return this.http.delete(LIKE_API + 'comment/' + commentId);
  }

  dislikeCommonImage(commonImageId: number): Observable<any> {
    return this.http.delete(LIKE_API + 'commonImage/' + commonImageId);
  }

  dislikeAudioFile(audioFileId: number): Observable<any> {
    return this.http.delete(LIKE_API + 'audioFile/' + audioFileId);
  }

  dislikeVideoFile(commonImageId: number): Observable<any> {
    return this.http.delete(LIKE_API + 'videoFile/' + commonImageId);
  }
}
