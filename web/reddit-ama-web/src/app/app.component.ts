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

  // Data
  isDarkMode = false;

  ngOnInit(): void {
    this.isDarkMode = localStorage.getItem('darkMode') === 'true';
    if (this.isDarkMode) {
      document.body.parentElement!.classList.add('dark');
    }
  }

  toggleDarkMode(): void {
    // toggle dark mode and save to localstorage
    this.isDarkMode = !this.isDarkMode;
    localStorage.setItem('darkMode', this.isDarkMode.toString());
    if (this.isDarkMode) {
      document.body.parentElement!.classList.add('dark');
    } else {
      document.body.parentElement!.classList.remove('dark');
    }
  }

}
