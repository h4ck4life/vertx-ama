import { Component } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

import { faCircleNotch, faAnglesDown } from '@fortawesome/free-solid-svg-icons';

import * as linkify from 'linkifyjs';
import linkifyHtml from 'linkify-html';

import { AppService } from '../app.service';
import { Reddit } from '../Reddit';
import { Datum, RedditList } from '../RedditList';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent {

  // Icons
  faCircleNotch = faCircleNotch;
  faAnglesDown = faAnglesDown;

  // Toggles
  isRandomAMALoaded = false;
  isShowDescription = false;
  isResultFound = false;
  showSeeMore = true;

  // Data
  redditId: string = '';
  redditList: RedditList = {
    total: 0,
    data: [],
    concat: function (data: Datum[]): RedditList {
      throw new Error('Function not implemented.');
    }
  };
  currentPage: number = 1;
  totalAnswer: number = 0;

  // 3rd libraries
  linkifyHtml = linkifyHtml;

  constructor(private appService: AppService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.redditId = this.route.snapshot.paramMap.get('id') as string;
    this.appService.getAllRedditById(this.redditId, this.currentPage).subscribe((response) => {
      if (response.data.length > 0) {
        response.data.map((reddit: Datum) => {
          reddit.body = linkifyHtml(reddit.body as string, { target: "_blank" }).replaceAll('\n', '<br>');
          reddit.answer = linkifyHtml(reddit.answer as string, { target: "_blank" }).replaceAll('\n', '<br>');
          reddit.question = linkifyHtml(reddit.question as string, { target: "_blank" }).replaceAll('\n', '<br>');
        });
        this.redditList.data = response.data;
        this.isRandomAMALoaded = true;
        this.isResultFound = true;
        this.totalAnswer = response.total;
        this.showSeeMore = response.data.length < 10 ? false : true;
      } else {
        this.isRandomAMALoaded = true;
        this.isResultFound = false;
        this.showSeeMore = false;
      }
    }
    )
  }

  viewMore(): void {
    this.currentPage++;
    this.appService.getAllRedditById(this.redditId, this.currentPage).subscribe((response) => {
      if (response.data.length > 0) {
        response.data.map((reddit: Datum) => {
          reddit.body = linkifyHtml(reddit.body as string, { target: "_blank" }).replaceAll('\n', '<br>');
          reddit.answer = linkifyHtml(reddit.answer as string, { target: "_blank" }).replaceAll('\n', '<br>');
          reddit.question = linkifyHtml(reddit.question as string, { target: "_blank" }).replaceAll('\n', '<br>');
        });
        this.redditList.data = this.redditList.data.concat(response.data);
        this.totalAnswer = response.total;
        this.showSeeMore = response.data.length < 10 ? false : true;
      } else {
        this.showSeeMore = false;
      }
    });
  }

  showDescription(): void {
    this.isShowDescription = !this.isShowDescription;
  }

}
