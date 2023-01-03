import { Component, OnDestroy } from '@angular/core';
import { Router } from "@angular/router";

import { faRedditAlien } from '@fortawesome/free-brands-svg-icons';
import { faMagnifyingGlass, faMoon, faSun } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  faRedditAlien = faRedditAlien;
  faMagnifyingGlass = faMagnifyingGlass;
  faMoon = faMoon;
  faSun = faSun;

  // Data
  isDarkMode = false;
  isShowSearch = false;
  searchQuery = '';


  constructor(private router: Router) { }

  ngOnInit(): void {
    // Check if dark mode is enabled
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

  // Toggle search bar
  toggleSearch(): void {
    this.isShowSearch = !this.isShowSearch;
  }

  search(): void {
    this.router.navigate(['view/search', this.searchQuery])
  }

}
