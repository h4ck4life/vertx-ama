import { Component, OnDestroy } from '@angular/core';
import { Router } from "@angular/router";

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
  searchQuery = '';


  constructor(private router: Router) { }

  ngOnInit(): void {

    this.isDarkMode = localStorage.getItem('darkMode') === 'true';
    if (this.isDarkMode) {
      document.body.parentElement!.classList.add('dark');
    }
  }

  // Toggle dark mode and save to localstorage
  toggleDarkMode(): void {
    this.isDarkMode = !this.isDarkMode;
    localStorage.setItem('darkMode', this.isDarkMode.toString());
    if (this.isDarkMode) {
      document.body.parentElement!.classList.add('dark');
    } else {
      document.body.parentElement!.classList.remove('dark');
    }
  }



  // bind the searchQuery to the input field and the search function to the search button
  search(): void {
    this.router.navigate(['/search', this.searchQuery])
  }

}
