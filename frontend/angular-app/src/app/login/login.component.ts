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
  errorMessage = '';
  roles: string[] = [];

  constructor(
    private storageService: StorageService,
    private userAuthService: UserAuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.roles = this.storageService.getUser().roles;
    }
  }

  login(loginForm: NgForm): void {
    const loginData: LoginRequestDTO = {
      username: loginForm.value.username,
      password: loginForm.value.password
    };

    this.userAuthService.login(loginData).subscribe({
      next: (response: LoginResponseDTO) => {
        this.storageService.saveUser(response);
        this.storageService.saveToken(response.jwt);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = response.roles;
        this.router.navigate(['/home']);
      },
      error: (error) => {
        this.isLoginFailed = true;
        this.errorMessage = 'Login failed. Please check your credentials.';
        console.log(error);
      }
    });
  }
}



