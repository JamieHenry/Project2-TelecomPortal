import { Component, OnInit } from '@angular/core';
import { AbstractControl, UntypedFormBuilder, UntypedFormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { lastValueFrom } from 'rxjs';
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
  styleUrls: ['./register.component.css', '../../assets/stylesheets/modal.css']
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

  showRegisterModal: boolean = false;

  constructor(private fb: UntypedFormBuilder, private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  async register() {
    this.error = '';
   
    let userResponse = await lastValueFrom(this.userService.findByEmail(this.email.toLowerCase()))
    
    if (userResponse.body !== null) {
      this.error = 'E-mail already in use'
      return;
    }

    await lastValueFrom(this.userService.register({
      'email': this.email.toLowerCase(),
      'password': this.password,
      'firstName': this.firstName,
      'lastName': this.lastName
    }));

    this.registerModal();

    // let route = this.router.config.find(r => r.path === 'login');
    // if (route) this.router.navigateByUrl('/login');
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

  checkPasswords(group: UntypedFormGroup) {
    let pass = group.controls?.['password'].value;
    let confirmPass = group.controls?.['confirmPassword'].value;

    if (!pass || !confirmPass) return null;

    return pass === confirmPass ? null : { notSame: true };
  }

  registerModal() {
    this.showRegisterModal = true;
  }

  cancelRegisterModal() {
    this.showRegisterModal = false;
  }
}
