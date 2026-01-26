import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { DynamicServiceService } from '../_services/api.service';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatToolbarModule } from '@angular/material/toolbar';

@Component({
  selector: 'app-form-list',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatProgressSpinnerModule,
    MatToolbarModule
  ],
  templateUrl: './form-list.component.html',
  styleUrls: ['./form-list.component.css']
})
export class FormListComponent implements OnInit {
  displayedColumns: string[] = ['formId', 'formName', 'actions'];
  dataSource = new MatTableDataSource<any>([]);
  loading = false;
  error = '';

  constructor(private apiService: DynamicServiceService, private router: Router) {}

  ngOnInit(): void {
    this.fetchForms();
  }

  fetchForms(): void {
    this.loading = true;
    this.apiService.getForm().subscribe({
      next: (forms) => {
        this.dataSource.data = forms;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load forms. Please try again later.';
        this.loading = false;
      }
    });
  }

  updateForm(formId: number): void {
    this.router.navigate(['/form-field-manager', formId]);
  }

  deleteForm(formId: number): void {
    if (confirm('Are you sure you want to delete this form? This action cannot be undone.')) {
      this.apiService.deleteForm(formId).subscribe({
        next: () => {
          this.fetchForms();
        },
        error: () => {
          this.error = 'Failed to delete form. Please try again.';
        }
      });
    }
  }

  addForm(): void {
    this.router.navigate(['/new-form']);
  }
}