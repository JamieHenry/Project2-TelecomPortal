import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../models/user.model';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  currentUser!: User | null;

  constructor(private authService: AuthenticationService, private router: Router) {
    this.authService.currentUser.subscribe(currUser => {
      this.currentUser = currUser;
    })
  }

  ngOnInit(): void {
  }

  navigate(url: string): void {
    this.router.navigateByUrl(`${url}`);
  }
}
