import { HttpClient } from '@angular/common/http';
import { Injectable, isDevMode } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Reddit } from './Reddit';
import { RedditList } from './RedditList';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private http: HttpClient) { }

  getReddit(): Observable<Reddit> {
    return this.http.get<Reddit>(this.getHostname() + '/api/random')
  }

  getAllRedditById(id: string, page: number): Observable<RedditList> {
    return this.http.get<RedditList>(this.getHostname() + '/api/' + id + '/page/' + page)
  }

  searchReddit(query: string, page: number): Observable<RedditList> {
    return this.http.get<RedditList>(this.getHostname() + '/api/search/' + query + '/page/' + page)
  }

  private getHostname(): string {
    return isDevMode() ? 'http://localhost:8080' : '';
  }

}
