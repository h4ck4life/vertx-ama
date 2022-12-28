import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Reddit } from './Reddit';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private http: HttpClient) { }

  private redditUrl = 'api/random';

  getReddit(): Observable<Reddit> {
    return this.http.get<Reddit>(this.redditUrl)
  }

}
