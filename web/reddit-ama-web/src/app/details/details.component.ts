import { Component } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

import { AppService } from '../app.service';
import { Reddit } from '../Reddit';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent {

  redditId: string = '';
  redditList: Reddit[] = [];

  constructor(private appService: AppService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.redditId = this.route.snapshot.paramMap.get('id') as string;
    this.appService.getAllRedditById(this.redditId).subscribe((data) => {
      console.log(data);
      this.redditList = data;
    })
  }
}
