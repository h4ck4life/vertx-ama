import { Component } from '@angular/core';

import { faRedditAlien } from '@fortawesome/free-brands-svg-icons';
import { faCircleNotch, faAnglesDown } from '@fortawesome/free-solid-svg-icons';

import * as linkify from 'linkifyjs';
import linkifyHtml from 'linkify-html';

import { AppService } from './app.service';
import { Reddit } from './Reddit';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  // Icons
  faRedditAlien = faRedditAlien;
  faCircleNotch = faCircleNotch;
  faAnglesDown = faAnglesDown;  

  // Toggles
  isRandomAMALoaded = false;
  isShowDescription = false;

  // 3rd libraries
  linkifyHtml = linkifyHtml;

  reddit: Reddit = {};

  constructor(private appService: AppService) { }

  ngOnInit(): void {
    this.appService.getReddit().subscribe((data) => {
      this.reddit = data;
      this.reddit.body = linkifyHtml(this.reddit.body as string, { target: "_blank" });
      this.reddit.answer = linkifyHtml(this.reddit.answer as string, { target: "_blank" });
      this.reddit.question = linkifyHtml(this.reddit.question as string, { target: "_blank" });
      this.isRandomAMALoaded = true;
    });
  }

  showDescription(): void {
    this.isShowDescription = !this.isShowDescription;
  }

}
