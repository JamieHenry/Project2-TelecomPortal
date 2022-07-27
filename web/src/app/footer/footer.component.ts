import { Component, OnInit } from '@angular/core';
import { User } from '../models/user.model';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

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
