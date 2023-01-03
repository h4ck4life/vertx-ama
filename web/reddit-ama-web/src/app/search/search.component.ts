import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { faCircleNotch } from '@fortawesome/free-solid-svg-icons';

import linkifyHtml from 'linkify-html';
import { AppService } from '../app.service';
import { Datum, RedditList } from '../RedditList';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {

  faCircleNotch = faCircleNotch;

  // State
  isSearchLoaded = false;
  isResultFound = false;
  showSeeMore = true;
  isShowDescription = false;

  // Data
  searchQuery = '';
  currentPage: number = 1;
  totalAnswer: number = 0;
  redditList: RedditList = {
    total: 0,
    data: [],
    concat: function (data: Datum[]): any {
      throw new Error('Function not implemented.');
    }
  };

  constructor(private route: ActivatedRoute, private appService: AppService) { }

  ngOnInit(): void {
    //this.searchQuery = this.route.snapshot.paramMap.get('query') as string;
    this.route.params.subscribe(params => {
      this.searchQuery = params['query'];
      this.searchReddit(this.searchQuery);
    });
    
    
  }

  private searchReddit(searchTerm: string) : void {
    this.appService.searchReddit(searchTerm, this.currentPage).subscribe((response) => {
      if (response.data.length > 0) {
        response.data.forEach((reddit: Datum) => {
          reddit.body = linkifyHtml(reddit.body as string, { target: "_blank" }).replaceAll('\n', '<br>');
          reddit.answer = linkifyHtml(reddit.answer as string, { target: "_blank" }).replaceAll('\n', '<br>');
          reddit.question = linkifyHtml(reddit.question as string, { target: "_blank" }).replaceAll('\n', '<br>');
        });
        this.redditList.data = response.data;
        this.isSearchLoaded = true;
        this.isResultFound = true;
        this.totalAnswer = response.total;
        this.showSeeMore = response.data.length < 10 ? false : true;
      } else {
        this.isSearchLoaded = true;
        this.isResultFound = false;
        this.showSeeMore = false;
      }
    });
  }

  viewMore(): void {
    this.currentPage++;
    this.appService.searchReddit(this.searchQuery, this.currentPage).subscribe((response) => {
      if (response.data.length > 0) {
        response.data.forEach((reddit: Datum) => {
          reddit.body = linkifyHtml(reddit.body as string, { target: "_blank" }).replaceAll('\n', '<br>');
          reddit.answer = linkifyHtml(reddit.answer as string, { target: "_blank" }).replaceAll('\n', '<br>');
          reddit.question = linkifyHtml(reddit.question as string, { target: "_blank" }).replaceAll('\n', '<br>');
        });
        this.redditList.data = this.redditList.data.concat(response.data);
        this.isSearchLoaded = true;
        this.isResultFound = true;
        this.totalAnswer = response.total;
        this.showSeeMore = response.data.length < 10 ? false : true;
      } else {
        this.isSearchLoaded = true;
        this.isResultFound = false;
        this.showSeeMore = false;
      }
    });
  }

  showDescription(): void {
    this.isShowDescription = !this.isShowDescription;
  }

}
