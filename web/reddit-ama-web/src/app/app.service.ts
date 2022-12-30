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
    return this.http.get<Reddit>(this.getHostname() + '/api/random')
  }

  getAllRedditById(id: string, page: number): Observable<Reddit[]> {
    return this.http.get<Reddit[]>(this.getHostname() + '/api/' + id + '/page/' + page)
  }

  private getHostname(): string {
    return isDevMode() ? 'http://localhost:8080' : '';
  }

}
