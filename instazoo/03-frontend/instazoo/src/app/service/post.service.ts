import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Post} from "../models/post";

const POST_API = 'http://localhost:8080/api/post/';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) { }

  createPost(post: Post): Observable<any> {
    return this.http.post(POST_API + 'create', post);
  }

  getAllPosts(): Observable<any> {
    return this.http.get(POST_API + 'all')
  }

  getPostForUser(id: number): Observable<any> {
    return this.http.get(POST_API + 'user/' + id);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(POST_API + id + '/delete');
  }

}
