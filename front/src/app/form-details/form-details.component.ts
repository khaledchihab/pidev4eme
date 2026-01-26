import { Component, OnInit } from '@angular/core';
import { DynamicServiceService } from '../_services/api.service';
import { ActivatedRoute } from '@angular/router';
import { AuthUserService } from '../_services/auth-user.service';
import { forkJoin } from 'rxjs';
import { UserResponseDTO } from '../dto/UserResponseDTO';

@Component({
  selector: 'app-form-details',
  templateUrl: './form-details.component.html',
  styleUrls: ['./form-details.component.css'],
})
export class FormDetailsComponent implements OnInit {
  formName: string = '';
  moyennes: any[] = [];
  formId: number = 0;
usersMap: Record<string, UserResponseDTO | undefined> = {};

  constructor(
    private moyenneService: DynamicServiceService,
    private userService: AuthUserService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // Subscribe to route params so it works even if route changes without reload
    this.route.params.subscribe(params => {
      this.formName = params['formName'];
      this.loadMoyennes();
    });
  }

  loadMoyennes(): void {
    this.moyenneService.getMoyennesByFormName(this.formName).subscribe({
      next: (data: any[]) => {
        this.moyennes = data;
        console.log("fetched data:",data);

        // Extract all unique userIds
        const userIds = Array.from(new Set(this.moyennes.map(m => m.userId)));

        // Fetch all users in parallel
        const userRequests = userIds.map(id => this.userService.getUserById(id));
        forkJoin(userRequests).subscribe({
          next: (users: UserResponseDTO[]) => {
            users.forEach(u => {
              if (u.id) this.usersMap[u.id] = u;
            });
            // Now you can use this.usersMap in the template
          },
          error: (err) => console.error('Error fetching users', err),
        });

        // Sort moyennes if needed
        this.moyennes.sort((a, b) => b.moyenne - a.moyenne);
      },
      error: (err) => console.error('Error fetching moyennes', err),
    });
  }

  onSendEmailsButtonClick(): void {
    this.moyenneService.getFormIdByName(this.formName).subscribe({
      next: (formId) => {
        this.formId = formId;
        this.sendEmails(this.formId);
      },
      error: (err) => console.error('Error fetching formId', err),
    });
  }

  sendEmails(formId: number): void {
    this.moyenneService.sendEmails(formId).subscribe({
      next: () => console.log('Emails sent successfully'),
      error: (err) => console.error('Error sending emails', err),
    });
  }
}
