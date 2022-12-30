import { Component } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

import { faCircleNotch, faAnglesDown } from '@fortawesome/free-solid-svg-icons';

import * as linkify from 'linkifyjs';
import linkifyHtml from 'linkify-html';

import { AppService } from '../app.service';
import { Reddit } from '../Reddit';

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
  hideSeeMore = false;

  // Data
  redditId: string = '';
  redditList: Reddit[] = [];
  currentPage: number = 1;

  // 3rd libraries
  linkifyHtml = linkifyHtml;

  constructor(private appService: AppService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.redditId = this.route.snapshot.paramMap.get('id') as string;
    this.appService.getAllRedditById(this.redditId, this.currentPage).subscribe((data) => {
      if (data.length > 0) {
        data.map((reddit: Reddit) => {
          reddit.body = linkifyHtml(reddit.body as string, { target: "_blank" }).replaceAll('\n', '<br>');
          reddit.answer = linkifyHtml(reddit.answer as string, { target: "_blank" }).replaceAll('\n', '<br>');
          reddit.question = linkifyHtml(reddit.question as string, { target: "_blank" }).replaceAll('\n', '<br>');
        });
        this.redditList = data;
        this.isRandomAMALoaded = true;
        this.isResultFound = true;
      } else {
        this.isRandomAMALoaded = true;
        this.isResultFound = false;
      }
    }
    )
  }

  viewMore(): void {
    this.currentPage++;
    this.appService.getAllRedditById(this.redditId, this.currentPage).subscribe((data) => {
      if (data.length > 0) {
        data.map((reddit: Reddit) => {
          reddit.body = linkifyHtml(reddit.body as string, { target: "_blank" }).replaceAll('\n', '<br>');
          reddit.answer = linkifyHtml(reddit.answer as string, { target: "_blank" }).replaceAll('\n', '<br>');
          reddit.question = linkifyHtml(reddit.question as string, { target: "_blank" }).replaceAll('\n', '<br>');
        });
        this.redditList = this.redditList.concat(data);
      } else {
        this.hideSeeMore = true;
      }
    });
  }

  showDescription(): void {
    this.isShowDescription = !this.isShowDescription;
  }
}
