import { HttpClient } from '@angular/common/http';
import { Injectable, isDevMode } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Reddit } from './Reddit';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private http: HttpClient) { }

  getReddit(): Observable<Reddit> {
    return this.http.get<Reddit>(this.getAPIUrl())
  }

  private getAPIUrl(): string {
    return isDevMode() ? 'http://localhost:8080/api/random' : 'api/random';
  }

}
