import { Component, OnInit } from '@angular/core';
import { User } from './models/user.model';
import { AuthenticationService } from './services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  currentUser!: User | null;

  constructor(private authService:AuthenticationService) { }

  ngOnInit(): void {
    this.authService.currentUser.subscribe(currUser => {
      this.currentUser = currUser;
    });
  }
}
