import { Component } from '@angular/core';
import { FormApplicationHistory } from '../dto/FormApplicationHistoryDTO';
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
    private service: FormapplicationService
  ) {}

  ngOnInit(): void {
    const userStr = localStorage.getItem('User');
    if (userStr) {
      const user = JSON.parse(userStr);
      this.userId = user.id;
      this.loadHistory();
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
