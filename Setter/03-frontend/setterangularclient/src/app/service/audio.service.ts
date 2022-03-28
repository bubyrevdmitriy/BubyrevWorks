import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AudioFile} from "../models/audio-file";
import {Observable} from "rxjs";
import {NewStreamFile} from "../models/new-stream-file";
import {Post} from "../models/post";

const AUDIO_API = 'http://localhost:8080/api/audio/';

@Injectable({
  providedIn: 'root'
})
export class AudioService {

  constructor(private http: HttpClient) { }


  public findById(id: number) {
    return this.http.get<AudioFile>(AUDIO_API + id).toPromise()
  }

  public findAll(descriptionSearch: string, thePage: number, thePageSize:  number) {
    if(descriptionSearch == null) {
      return this.http.get<AudioFile[]>(AUDIO_API + 'all?page=' + thePage + '&size=' + thePageSize).toPromise();
    } else {
      return this.http.get<AudioFile[]>(AUDIO_API + 'all?page=' + thePage + '&size=' + thePageSize + '&descriptionSearch=' + descriptionSearch).toPromise();
    }
  }

  countAll(descriptionSearch: string): Observable<any> {
    if(descriptionSearch == null) {
      return this.http.get(AUDIO_API + 'count/all')
    } else {
      return this.http.get(AUDIO_API + 'count/all' + '?descriptionSearch=' + descriptionSearch)
    }
  }

  public findAllAudiosFromUser(id: number, thePage: number, thePageSize:  number) {
    return this.http.get<AudioFile[]>(AUDIO_API + 'user/' + id+ '?page=' + thePage + '&size=' + thePageSize).toPromise()
  }
  countAllAudiosForUser(id: number): Observable<any> {
    //need to build URL based on page and size
    const searchUrl = AUDIO_API + 'user/count/' + id;
    return this.http.get<any>(searchUrl);
  }

  public findAllAudiosFromGroup(id: number, thePage: number, thePageSize:  number) {
    return this.http.get<AudioFile[]>(AUDIO_API + 'group/' + id + '?page=' + thePage + '&size=' + thePageSize).toPromise()
  }
  countAllAudiosForUserGroup(id: number): Observable<any> {
    //need to build URL based on page and size
    const searchUrl = AUDIO_API + 'group/count/' + id;
    return this.http.get<any>(searchUrl);
  }

  /*public uploadNewAudio(formData: FormData) {
    console.log(AUDIO_API + 'upload');
    return this.http.post(AUDIO_API + 'upload', formData).toPromise()
  }*/

  uploadNewAudio(newStreamFile: NewStreamFile, file: File): Observable<any> {
    const uploadData = new FormData();
    uploadData.append('file', file);
    uploadData.append('description', newStreamFile.description);
    uploadData.append('groupId', newStreamFile.groupId);

    console.log(AUDIO_API + 'upload');
    console.log(newStreamFile.description);
    console.log(file);
    console.log(newStreamFile.groupId);
    return this.http.post(AUDIO_API + 'upload', uploadData);
  }
/*
  createPostInUserGroup(post: Post, id: number): Observable<any> {
    return this.http.post(POST_API + 'group/' + id + '/create', post);
  }
*/
  deleteAudioFile(audioFileId: number): Observable<any> {
    return this.http.delete(AUDIO_API + audioFileId);
  }

}
