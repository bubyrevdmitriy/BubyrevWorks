import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const IMAGE_API = 'http://localhost:8080/api/image/';

@Injectable({
  providedIn: 'root'
})
export class ImageUploadService {

  constructor(private http: HttpClient) { }

  uploadImageToUser(file: File): Observable<any> {
    const uploadData = new FormData();
    uploadData.append('file', file);
    return this.http.post(IMAGE_API + 'userPhoto', uploadData);
  }

  uploadImageToUserGroup(file: File, id: number): Observable<any> {
    const uploadData = new FormData();
    uploadData.append('file', file);
    return this.http.post(IMAGE_API + 'group/' + id + '/userGroupPhoto', uploadData);
  }

  uploadImageToPost(file: File, postId: number): Observable<any> {
    const uploadData = new FormData();
    uploadData.append('file', file);

    return this.http.post(IMAGE_API + postId + '/upload', uploadData);
  }

  getImagesForUser(id: number): Observable<any> {
    return this.http.get(IMAGE_API + 'user/' + id);
  }

  getAllImagesForUserGroup(id: number): Observable<any> {
    return this.http.get(IMAGE_API + 'group/' + id);
  }

  getImagesForUserPaginate(id: number, thePage: number, thePageSize:  number): Observable<any> {
    //need to build URL based on page and size
    const searchUrl = IMAGE_API + 'user/' + id + '?page=' + thePage + '&size=' + thePageSize;
    return this.http.get<any>(searchUrl);
  }

  countImagesForUser(id: number): Observable<any> {
    //need to build URL based on page and size
    const searchUrl = IMAGE_API + 'user/count/' + id;
    return this.http.get<any>(searchUrl);
  }

  getAllImagesForUserGroupPaginate(id: number, thePage: number, thePageSize:  number): Observable<any> {
    //need to build URL based on page and size
    const searchUrl = IMAGE_API + 'group/' + id + '?page=' + thePage + '&size=' + thePageSize;
    return this.http.get<any>(searchUrl);
  }

  countAllImagesForUserGroup(id: number): Observable<any> {
    //need to build URL based on page and size
    const searchUrl = IMAGE_API + 'group/count/' + id;
    return this.http.get<any>(searchUrl);
  }

  getImageById(id: number): Observable<any> {
    return this.http.get(IMAGE_API + id);
  }

  deleteImage(imageId: number): Observable<any> {
    return this.http.delete(IMAGE_API + imageId);
  }

}
