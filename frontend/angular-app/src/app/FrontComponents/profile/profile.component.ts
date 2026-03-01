import { Component, OnInit } from '@angular/core';
import { AuthUserService } from '../../_services/auth-user.service';
import { StorageService } from '../../_services/storage.service';
import { UserResponseDTO } from '../../dto/UserResponseDTO';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser?: Partial<UserResponseDTO>;
  errorMessage = '';

  constructor(
    private authUserService: AuthUserService,
    private storageService: StorageService
  ) { }

  ngOnInit(): void {
    const cachedUser = this.storageService.getUser();
    if (cachedUser && Object.keys(cachedUser).length > 0) {
      this.currentUser = cachedUser;
    }

    this.authUserService.getUserProfile().subscribe({
      next: (user) => {
        this.currentUser = user;
      },
      error: () => {
        const username = this.currentUser?.username || this.authUserService.getUsername();
        if (!username) {
          this.errorMessage = 'Live profile data is unavailable. Showing available session data.';
          return;
        }

        this.authUserService.getUserByUsername(username).subscribe({
          next: (user) => {
            this.currentUser = user;
            this.errorMessage = '';
          },
          error: () => {
            this.errorMessage = 'Live profile data is unavailable. Showing available session data.';
          }
        });
      }
    });
  }
}