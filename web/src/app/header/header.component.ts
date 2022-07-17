import { Component, OnInit } from '@angular/core';
import { User } from '../models/user.model';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  currentUser!: User | null;

  constructor(private authService: AuthenticationService) {
    this.authService.currentUser.subscribe(currUser => {
      this.currentUser = currUser;
    })
  }

  ngOnInit(): void {
  }

  logout() {
    this.authService.logout();
    this.currentUser = null;
  }
}
