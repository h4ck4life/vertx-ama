import { Component } from '@angular/core';

import { faRedditAlien } from '@fortawesome/free-brands-svg-icons';
import { faMagnifyingGlass, faCircleHalfStroke } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  faRedditAlien = faRedditAlien;
  faMagnifyingGlass = faMagnifyingGlass;
  faCircleHalfStroke = faCircleHalfStroke;

  toggleDarkMode(): void {
    document.body.parentElement!.classList.toggle('dark');
  }

}
