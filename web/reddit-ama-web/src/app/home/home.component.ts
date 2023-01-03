import { Component } from '@angular/core';

import { faCircleNotch, faAnglesDown } from '@fortawesome/free-solid-svg-icons';
import linkifyHtml from 'linkify-html';

import { AppService } from '../app.service';
import { Reddit } from '../Reddit';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  // Icons
  faCircleNotch = faCircleNotch;
  faAnglesDown = faAnglesDown;

  // Toggles
  isRandomAMALoaded = false;
  isShowDescription = false;

  // 3rd libraries
  linkifyHtml = linkifyHtml;

  // Data
  reddit: Reddit = {
    total: 0,
    data: {}
  };
  totalAnswer: number = 0;

  constructor(private appService: AppService) { }

  ngOnInit(): void {
    this.appService.getReddit().subscribe((data) => {
      this.reddit = data;
      this.reddit.data.body = linkifyHtml(this.reddit.data.body as string, { target: "_blank" }).replaceAll('\n', '<br>');
      this.reddit.data.answer = linkifyHtml(this.reddit.data.answer as string, { target: "_blank" }).replaceAll('\n', '<br>');
      this.reddit.data.question = linkifyHtml(this.reddit.data.question as string, { target: "_blank" }).replaceAll('\n', '<br>');
      this.isRandomAMALoaded = true;
      this.totalAnswer = data.total;
    });
  }

  showDescription(): void {
    this.isShowDescription = !this.isShowDescription;
  }


}
