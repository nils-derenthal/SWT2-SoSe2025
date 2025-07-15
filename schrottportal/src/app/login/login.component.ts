import { Component, inject, signal } from '@angular/core';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import {
  MatFormField,
  MatInput,
  MatInputModule,
  MatLabel,
} from '@angular/material/input';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../auth.service';
import { finalize } from 'rxjs';

@Component({
  selector: 'app-login',
  imports: [
    ReactiveFormsModule,
    MatInput,
    MatFormField,
    MatLabel,
    MatInputModule,
  ],
  templateUrl: './login.component.html',
  standalone: true,
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  http = inject(HttpClient);
  auth = inject(AuthService);

  readonly ERROR_RESPONSE = 'error_response';

  login = new FormGroup({
    email: new FormControl<string>('', {
      nonNullable: true,
      validators: [Validators.email, Validators.required],
    }),
    password: new FormControl<string>('', {
      nonNullable: true,
      validators: [Validators.required],
    }),
  });

  signup = new FormGroup({
    email: new FormControl<string>('', {
      nonNullable: true,
      validators: [Validators.email, Validators.required],
    }),
    firstname: new FormControl<string>('', {
      nonNullable: true,
      validators: [Validators.required],
    }),
    lastname: new FormControl<string>('', {
      nonNullable: true,
      validators: [Validators.required],
    }),
    password: new FormControl<string>('', {
      nonNullable: true,
      validators: [Validators.required],
    }),
  });

  loadingLogin = signal(false);
  loadingSignUp = signal(false);

  onError<T extends { [K in keyof T]: AbstractControl }>(form: FormGroup<T>) {
    form.reset();
    form.setErrors({ [this.ERROR_RESPONSE]: true });
    this.auth.deleteCredentials();
  }

  logIn() {
    this.loadingLogin.set(true);
    this.auth
      .login(this.login.getRawValue())
      .pipe(finalize(() => this.loadingLogin.set(false)))
      .subscribe({
        error: () => this.onError(this.login),
      });
  }

  signUp() {
    this.loadingSignUp.set(true);
    this.auth
      .signup(this.signup.getRawValue())
      .pipe(finalize(() => this.loadingSignUp.set(false)))
      .subscribe({
        error: () => this.onError(this.signup),
      });
  }
}
