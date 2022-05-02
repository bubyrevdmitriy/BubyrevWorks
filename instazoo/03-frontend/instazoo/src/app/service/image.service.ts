import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const IMAGE_API = 'http://localhost:8080/api/image/';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private http: HttpClient) { }

  uploadImageToUser(file: File): Observable<any> {
    const uploadData = new FormData();
    uploadData.append('file', file);
    return this.http.post(IMAGE_API + 'upload', uploadData);
  }

  uploadImageToPost(file: File, postId: number): Observable<any> {
    const uploadData = new FormData();
    uploadData.append('file', file);
    return this.http.post(IMAGE_API + postId + '/upload', uploadData);
  }

  getProfileImage(userId: number): Observable<any> {
    return this.http.get(IMAGE_API + 'user/' + userId);
  }

  getPostImage(postId: number): any {
    return this.http.get(IMAGE_API + 'post/' + postId);
  }
}