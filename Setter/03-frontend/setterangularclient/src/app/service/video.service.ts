import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {VideoFile} from "../models/video-file";
import {NewStreamFile} from "../models/new-stream-file";
import {Observable} from "rxjs";

const VIDEO_API = 'http://localhost:8080/api/video/';

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  constructor(private http: HttpClient) { }

  public findById(id: number) {
    return this.http.get<VideoFile>(VIDEO_API + id).toPromise()
  }

  public findAllPreviews(descriptionSearch: string, thePage: number, thePageSize:  number) {
    if(descriptionSearch == null) {
      return this.http.get<VideoFile[]>(VIDEO_API + 'all?page=' + thePage + '&size=' + thePageSize).toPromise();
    } else {
      return this.http.get<VideoFile[]>(VIDEO_API + 'all?page=' + thePage + '&size=' + thePageSize + '&descriptionSearch=' + descriptionSearch).toPromise();
    }
  }

  countAllPreviews(descriptionSearch: string): Observable<any> {
    if(descriptionSearch == null) {
      return this.http.get(VIDEO_API + 'count/all')
    } else {
      return this.http.get(VIDEO_API + 'count/all' + '?descriptionSearch=' + descriptionSearch)
    }
  }

  public findAllPreviewsFromUser(id: number, thePage: number, thePageSize:  number) {
    return this.http.get<VideoFile[]>(VIDEO_API + 'user/' + id + '?page=' + thePage + '&size=' + thePageSize).toPromise()
  }
  countPreviewsForUser(id: number): Observable<any> {
    //need to build URL based on page and size
    const searchUrl = VIDEO_API + 'user/count/' + id;
    return this.http.get<any>(searchUrl);
  }

  public findAllPreviewsFromGroup(id: number, thePage: number, thePageSize:  number) {
    return this.http.get<VideoFile[]>(VIDEO_API + 'group/' + id + '?page=' + thePage + '&size=' + thePageSize).toPromise()
  }
  countPreviewsForUserGroup(id: number): Observable<any> {
    //need to build URL based on page and size
    const searchUrl = VIDEO_API + 'group/count/' + id;
    return this.http.get<any>(searchUrl);
  }

  uploadNewVideo(newStreamFile: NewStreamFile, file: File, filePreview: File): Observable<any> {
    const uploadData = new FormData();
    console.log('uploadNewVideo(newStreamFile: NewStreamFile, file: File, filePreview: File): Observable<any> {');
    uploadData.append('file', file);
    uploadData.append('filePreview', filePreview);
    uploadData.append('description', newStreamFile.description);
    uploadData.append('groupId', newStreamFile.groupId);

    return this.http.post(VIDEO_API + 'upload', uploadData);
  }

  deleteVideoFile(id: number): Observable<any> {
    return this.http.delete(VIDEO_API + id);
  }

}
