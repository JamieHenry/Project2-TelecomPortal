import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { User } from '../models/user.model';
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

  constructor(private fb: UntypedFormBuilder, private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  async login() {
    this.error = '';
    let user: User = new User(0, this.email!.toLowerCase(), '', '', this.password!);
    
    let userResponse = await lastValueFrom(this.userService.login(user.email, user.password));

    if (userResponse.body === null) {
      this.error = 'Invalid email/password';
      return;
    }

    user = userResponse.body;

    console.log(user);

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
