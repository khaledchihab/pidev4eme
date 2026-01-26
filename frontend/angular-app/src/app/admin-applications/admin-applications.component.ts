import { Component, OnInit } from '@angular/core';
import { FormapplicationService, FormApplication } from '../_services/formapplication.service';

@Component({
  selector: 'app-admin-applications',
  templateUrl: './admin-applications.component.html',
  styleUrls: ['./admin-applications.component.css']
})
export class AdminApplicationsComponent implements OnInit {
  applications: FormApplication[] = [];
  isLoading = true;
  errorMessage = '';

  constructor(private applicationService: FormapplicationService) {}

  ngOnInit(): void {
    this.loadApplications();
  }

  loadApplications(): void {
    this.isLoading = true;
    this.applicationService.getAllApplications().subscribe({
      next: (data) => {
        this.applications = data;
        console.log('Loaded applications:', this.applications);
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Error loading applications:', err);
        this.errorMessage = 'Failed to load applications';
        this.isLoading = false;
      }
    });
  }

  updateStatus(appId: number, newStatus: string): void {
    if (confirm(`Are you sure you want to ${newStatus} this application?`)) {
      this.applicationService.updateApplicationStatus(appId, newStatus).subscribe({
        next: (updatedApp) => {
          console.log('Application updated:', updatedApp);
          // Update the local array
          const index = this.applications.findIndex(app => app.id === appId);
          if (index !== -1) {
            this.applications[index] = updatedApp;
          }
        },
        error: (err) => {
          console.error('Error updating status:', err);
          alert('Failed to update application status');
        }
      });
    }
  }

  getStatusBadgeClass(status: string): string {
    switch (status) {
      case 'ACCEPTED':
        return 'badge bg-success';
      case 'REFUSED':
        return 'badge bg-danger';
      case 'PENDING':
        return 'badge bg-warning text-dark';
      default:
        return 'badge bg-secondary';
    }
  }

  getFormName(app: FormApplication): string {
    return app.form?.formName || 'N/A';
  }
}
