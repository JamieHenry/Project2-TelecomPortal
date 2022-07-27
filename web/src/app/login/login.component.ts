import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { User } from '../models/user.model';
import { AuthenticationService } from '../services/authentication.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm = this.fb.group({
    email: ['', Validators.required],
    password: ['', Validators.required]
  });
  error: string = '';

  constructor(private authService: AuthenticationService, private fb: UntypedFormBuilder, private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  async login() {
    this.error = '';
    
    let userResponse = await lastValueFrom(this.userService.login(this.email.toLowerCase(), this.password));

    if (userResponse.body === null) {
      this.error = 'Invalid email/password';
      return;
    }

    const user = userResponse.body!.user;

    user.password = '';

    this.authService.setValues(user, userResponse.body!.accessToken);

    let route = this.router.config.find(r => r.path === 'dashboard');
    if (route) this.router.navigateByUrl('/dashboard');
  }

  get email() {
    return this.loginForm.value['email']!;
  }

  get password() {
    return this.loginForm.value['password']!;
  }
}
