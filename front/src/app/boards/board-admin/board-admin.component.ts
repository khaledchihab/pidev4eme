import { Component, OnInit } from '@angular/core';
import { DynamicServiceService } from '../../_services/api.service';
import { AuthUserService } from 'src/app/_services/auth-user.service';
import { UserResponseDTO } from 'src/app/dto/UserResponseDTO';
import { FormapplicationService } from 'src/app/_services/formapplication.service';

@Component({
  selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.css']
})
export class BoardAdminComponent implements OnInit {
  applications: any[] = [];
  userDataMap: { [userId: string]: UserResponseDTO } = {};

  constructor(
    private formService: DynamicServiceService,
    private userService: AuthUserService,
    private formApplicationService : FormapplicationService
  ) {}

  ngOnInit(): void {
    this.loadApplications();
  }

  loadApplications() {
    this.formService.getAllApplications().subscribe({
      next: (apps: any[]) => {
        this.applications = apps;
        console.log('Applications loaded:', this.applications);

        // Ensure userIds are typed as strings
        const uniqueUserIds: string[] = Array.from(
          new Set(apps.map((a) => String(a.userId)))
        );

        uniqueUserIds.forEach((id: string) => {
          this.userService.getUserById(id).subscribe({
            next: (user: UserResponseDTO) => {
              this.userDataMap[id] = user;
            },
            error: (err) => console.error('Error fetching user', err)
          });
        });
      },
      error: (err) => console.error('Error loading applications', err)
    });
  }

  updateStatus(appId: number, newStatus: string) {
    this.formApplicationService.updateApplicationStatus(appId, newStatus).subscribe({
      next: () => {
        const app = this.applications.find((a) => a.id === appId);
        if (app) {
          app.status = newStatus;
        }
      },
      error: (err) => console.error('Error updating status', err)
    });
  }
}
