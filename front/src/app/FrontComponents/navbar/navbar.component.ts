import { Component } from '@angular/core';
import { AuthUserService } from 'src/app/_services/auth-user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  title = 'frontend-mobilite';
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  username?: string;

  constructor(private authUserService: AuthUserService, private router: Router) {}

  ngOnInit(): void {
    this.isLoggedIn = this.authUserService.isLoggedIn();
    console.log('Is user logged in?', this.isLoggedIn);
    console.log('User roles:', this.authUserService.getRoles());

    if (this.isLoggedIn) {
      this.showAdminBoard = this.authUserService.hasRole('SUPER_ADMIN');
      this.showModeratorBoard = this.authUserService.hasRole('UNIVERSITY');
      //this.username = this.authUserService.getUsername();
    }
  }

  logout(): void {
    this.authUserService.logout();
    window.location.reload();
  }
}
 