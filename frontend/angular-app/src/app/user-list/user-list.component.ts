import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { UserResponseDTO } from '../dto/UserResponseDTO';
import { AuthUserService } from '../_services/auth-user.service';

interface CustomPageEvent {
  pageIndex: number;
  pageSize: number;
  length: number;
}

@Component({
  selector: 'app-user-list',
  standalone: true,
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css'],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule
  ]
})
export class UserListComponent implements OnInit {
  users: UserResponseDTO[] = [];
  
  totalItems = 0;
  pageSize = 10;
  currentPage = 0;
  currentSearch = '';
  totalPages = 0;

  constructor(private userService: AuthUserService) {}

  ngOnInit(): void {
    this.fetchUsers();
  }

  fetchUsers(): void {
    if (this.currentSearch.trim()) {
      this.userService.searchUsers(this.currentSearch, this.currentPage, this.pageSize).subscribe(data => {
        this.users = data.content;
        this.totalItems = data.totalElements;
        this.totalPages = Math.ceil(this.totalItems / this.pageSize);
      });
    } else {
      this.userService.getAllUsers(this.currentPage, this.pageSize).subscribe(data => {
        this.users = data.content;
        this.totalItems = data.totalElements;
        this.totalPages = Math.ceil(this.totalItems / this.pageSize);
      });
    }
  }

  onSearch(event: any): void {
    this.currentSearch = event.target.value.trim();
    this.currentPage = 0;
    this.fetchUsers();
  }

  onPageChange(event: CustomPageEvent): void {
    this.pageSize = event.pageSize;
    this.currentPage = event.pageIndex;
    this.fetchUsers();
  }
}