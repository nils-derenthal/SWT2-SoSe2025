import {Component, input, output} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatFormField, MatInput, MatInputModule, MatLabel} from "@angular/material/input";

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, MatInput, MatFormField, MatLabel, MatInputModule],
  templateUrl: './login.component.html',
  standalone: true,
  styleUrl: './login.component.css'
})
export class LoginComponent {
  credentials = output<{
    email: string,
    password: string
  }>();

  type = input<'login' | 'register'>('login');

  login = new FormGroup({
    email: new FormControl<string>('', {nonNullable: true, validators: [Validators.email, Validators.required]}),
    password: new FormControl<string>('', {nonNullable: true, validators: [Validators.required]}),
  });

  logIn() {
    this.credentials.emit(this.login.getRawValue())
  }
}
