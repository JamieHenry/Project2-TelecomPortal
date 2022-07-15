import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { User } from '../models/user.model';
import { UserService } from '../services/user.service';

export function emailValidator(): ValidatorFn {
  return (control:AbstractControl) : ValidationErrors | null => {
    const value = control.value;

    if (!value) return null;

    return (value === '' || (value.includes('@') && value.includes('.'))) ? null : { invalidEmail: true };
  }
}

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUserForm = this.fb.group({
    firstName: ['', Validators.required],
    lastName: ['', Validators.required],
    email: ['', [Validators.required, emailValidator()]],
    password: ['', [Validators.required, Validators.minLength(6)]],
    confirmPassword: ['']
  }, { validators: this.checkPasswords });
  error: String = '';

  constructor(private fb: FormBuilder, private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  async register() {
    this.error = '';
    let user: User = new User(0, this.email!.toLowerCase(), this.firstName!, this.lastName!, this.password!);
    
    let userResponse = await lastValueFrom(this.userService.findByEmail(user.email))
    
    if (userResponse.body !== null) {
      this.error = 'Email already in use'
      return;
    }

    userResponse = await lastValueFrom(this.userService.register(user));
    
    console.log(userResponse.body);

    let route = this.router.config.find(r => r.path === 'login');
    if (route) this.router.navigateByUrl('/login');
  }

  get firstName() {
    return this.newUserForm.value['firstName']!;
  } 

  get lastName() {
    return this.newUserForm.value['lastName']!;
  }
   
  get email() {
    return this.newUserForm.value['email']!;
  } 

  get password() {
    return this.newUserForm.value['password']!;
  } 

  checkPasswords(group: FormGroup) {
    let pass = group.controls?.['password'].value;
    let confirmPass = group.controls?.['confirmPassword'].value;

    if (!pass || !confirmPass) return null;

    return pass === confirmPass ? null : { notSame: true };
  }
}
