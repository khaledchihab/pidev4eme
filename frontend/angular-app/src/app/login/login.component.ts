import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserAuthService } from '../_services/user-auth.service';
import { StorageService } from '../_services/storage.service';
import { Router } from '@angular/router';
import { LoginRequestDTO } from '../dto/LoginRequestDTO';
import { LoginResponseDTO } from '../dto/LoginResponseDTO';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  isLoggedIn = false;
  isLoginFailed = false;
  isLoading = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(
    private storageService: StorageService,
    private userAuthService: UserAuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    // If user already has a valid token, skip login entirely
    if (this.userAuthService.isTokenValid()) {
      this.isLoggedIn = true;
      const user = this.storageService.getUser();
      if (user && user.roles) {
        this.roles = user.roles;
      }
      this.router.navigate(['/home']);
      return;
    }

    // If token exists but is expired, clear it
    if (this.storageService.isLoggedIn()) {
      this.userAuthService.clear();
    }
  }

  login(loginForm: NgForm): void {
    this.isLoading = true;
    this.isLoginFailed = false;
    this.errorMessage = '';

    const loginData: LoginRequestDTO = {
      username: loginForm.value.username,
      password: loginForm.value.password
    };

    this.userAuthService.login(loginData).subscribe({
      next: (response: LoginResponseDTO) => {
        this.storageService.saveUser(response);
        this.storageService.saveToken(response.jwt);
        this.userAuthService.setRoles(response.roles);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.isLoading = false;
        this.roles = response.roles;
        this.router.navigate(['/home']);
      },
      error: (error: any) => {
        this.isLoginFailed = true;
        this.isLoading = false;

        if (error.status === 0) {
          this.errorMessage = 'Cannot reach the server. Please make sure all services are running and try again.';
        } else if (error.status === 401 || error.status === 403) {
          this.errorMessage = 'Invalid username or password.';
        } else if (error.status >= 500) {
          this.errorMessage = 'Server error. The authentication service may be starting up. Please try again in a few seconds.';
        } else {
          this.errorMessage = 'Login failed. Please try again.';
        }
        console.error('Login error:', error);
      }
    });
  }
}
