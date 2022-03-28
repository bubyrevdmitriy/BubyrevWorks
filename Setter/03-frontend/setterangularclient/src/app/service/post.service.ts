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

  createPostInUserGroup(post: Post, id: number): Observable<any> {
    return this.http.post(POST_API + 'group/' + id + '/create', post);
  }






  getAllPosts(captionSearch: string): Observable<any> {
    if(captionSearch == null) {
      return this.http.get(POST_API + 'all')
    } else {
      return this.http.get(POST_API + 'all' + '?captionSearch=' + captionSearch)
    }
  }
  getAllPostsPaginate(captionSearch: string, thePage: number, thePageSize:  number): Observable<any> {
    if(captionSearch == null) {
      return this.http.get(POST_API + 'all?page=' + thePage + '&size=' + thePageSize);
    } else {
      return this.http.get(POST_API + 'all?page=' + thePage + '&size=' + thePageSize + '&captionSearch=' + captionSearch);
    }
  }
  countAllPosts(captionSearch: string): Observable<any> {
    if(captionSearch == null) {
      return this.http.get(POST_API + 'count/all')
    } else {
      return this.http.get(POST_API + 'count/all' + '?captionSearch=' + captionSearch)
    }
  }












  getPostForUser(id: number): Observable<any> {
    return this.http.get(POST_API + 'user/' + id);
  }
  getPostsForUserPaginate(id: number, thePage: number, thePageSize:  number): Observable<any> {
    //need to build URL based on page and size
    const searchUrl = POST_API + 'user/' + id + '?page=' + thePage + '&size=' + thePageSize;
    return this.http.get<any>(searchUrl);
  }
  countPostsForUser(id: number): Observable<any> {
    //need to build URL based on page and size
    const searchUrl = POST_API + 'user/count/' + id;
    return this.http.get<any>(searchUrl);
  }








  getPostForUserGroup(id: number): Observable<any> {
    return this.http.get(POST_API + 'group/' + id);
  }
  getPostsForUserGroupPaginate(id: number, thePage: number, thePageSize:  number): Observable<any> {
    //need to build URL based on page and size
    const searchUrl = POST_API + 'group/' + id + '?page=' + thePage + '&size=' + thePageSize;
    return this.http.get<any>(searchUrl);
  }

  countPostsForUserGroup(id: number): Observable<any> {
    //need to build URL based on page and size
    const searchUrl = POST_API + 'group/count/' + id;
    return this.http.get<any>(searchUrl);
  }










  delete(id: number): Observable<any> {
    return this.http.delete(POST_API + id);
  }

  getPostById(id: number): Observable<any> {
    return this.http.get(POST_API + id);
  }
}
