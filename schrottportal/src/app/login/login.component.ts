import {Component, inject, input, output} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatFormField, MatInput, MatInputModule, MatLabel} from "@angular/material/input";
import {MatIcon} from "@angular/material/icon";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../auth.service";

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, MatInput, MatFormField, MatLabel, MatInputModule, MatIcon],
  templateUrl: './login.component.html',
  standalone: true,
  styleUrl: './login.component.css'
})
export class LoginComponent {
  http = inject(HttpClient);
  auth = inject(AuthService);

  login = new FormGroup({
    email: new FormControl<string>('', {nonNullable: true, validators: [Validators.email, Validators.required]}),
    password: new FormControl<string>('', {nonNullable: true, validators: [Validators.required]}),
  });

  signup = new FormGroup({
    email: new FormControl<string>('', {nonNullable: true, validators: [Validators.email, Validators.required]}),
    firstname: new FormControl<string>('', {nonNullable: true, validators: [Validators.required]}),
    lastname: new FormControl<string>('', {nonNullable: true, validators: [Validators.required]}),
    password: new FormControl<string>('', {nonNullable: true, validators: [Validators.required]}),
  });

  logIn() {

  }

  signUp() {
    this.auth.signup(this.signup.getRawValue());
  }
}
