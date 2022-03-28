import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const FEED_API = 'http://localhost:8080/api/feed/';

@Injectable({
  providedIn: 'root'
})
export class FeedService {

  constructor(private http: HttpClient) { }

  getAllFeed(thePage: number, thePageSize:  number): Observable<any> {
    return this.http.get(FEED_API + 'all?page=' + thePage + '&size=' + thePageSize)
  }
  countAllFeed(): Observable<any> {
    return this.http.get(FEED_API + 'count/all')
  }
}
