import { Component } from '@angular/core';
import { FormApplicationHistory } from '../dto/FormApplicationHistoryDTO';
import { DynamicServiceService } from '../_services/api.service';
import { AuthUserService } from '../_services/auth-user.service';
import { FormapplicationService } from '../_services/formapplication.service';

@Component({
  selector: 'app-application-history',
  templateUrl: './application-history.component.html',
  styleUrls: ['./application-history.component.css']
})
export class ApplicationHistoryComponent {
userId!: string;
  applications: FormApplicationHistory[] = [];
  isLoading = true;

  constructor(
    private service: FormapplicationService,
    private userService: AuthUserService
  ) {}

  ngOnInit(): void {
    const username = localStorage.getItem('Username');
    if (username) {
      this.userService.getUserByUsername(username).subscribe({
        next: (user) => {
          this.userId = user.id;
          this.loadHistory();
        },
        error: (err) => console.error('Error fetching user', err)
      });
    }
  }

  loadHistory() {
    this.isLoading = true;
    this.service.getApplicationsByUser(this.userId).subscribe({
      next: (data) => {
        this.applications = data;
        console.log('Fetched applications:', this.applications);
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Error fetching history', err);
        this.isLoading = false;
      }
    });
  }
}
