import {Component, inject, input, output, signal} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatFormField, MatInput, MatInputModule, MatLabel} from "@angular/material/input";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../auth.service";
import {MatProgressSpinner} from "@angular/material/progress-spinner";

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, MatInput, MatFormField, MatLabel, MatInputModule, MatProgressSpinner],
  templateUrl: './login.component.html',
  standalone: true,
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  http = inject(HttpClient);
  auth = inject(AuthService);

  readonly ERROR_RESPONSE = 'error_response';

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

  loadingLogin = signal(false);
  loadingSignUp = signal(false);

  onError<T extends { [K in keyof T]: AbstractControl }>(form: FormGroup<T>) {
    form.reset();
    form.setErrors({[this.ERROR_RESPONSE]: true});
  }

  logIn() {
    this.loadingLogin.set(true);
    this.auth.login(this.login.getRawValue())
      .subscribe({
        error: () => this.onError(this.login),
        complete: () => this.loadingLogin.set(false),
      });
  }

  signUp() {
    this.loadingSignUp.set(true);
    this.auth.signup(this.signup.getRawValue())
      .subscribe({
        error: () => this.onError(this.signup),
        complete: () => this.loadingSignUp.set(false),
      });
  }
}
