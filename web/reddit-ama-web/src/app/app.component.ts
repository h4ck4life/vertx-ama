import { Component } from '@angular/core';
import { faRedditAlien } from '@fortawesome/free-brands-svg-icons';
import { AppService } from './app.service';
import { Reddit } from './Reddit';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  faRedditAlien = faRedditAlien;
  isRandomAMALoaded = false;
  reddit: Reddit = {};

  constructor(private appService: AppService) { }

  ngOnInit(): void {
    this.appService.getReddit().subscribe((data) => {
      this.reddit = data;
      this.isRandomAMALoaded = true;
    });
  }

}
