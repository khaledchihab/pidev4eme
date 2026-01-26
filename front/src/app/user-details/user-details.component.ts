import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { UserResponseDTO } from '../dto/UserResponseDTO';
import { UserRoleRequestDTO } from '../dto/UserRoleRequestDTO';

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RoleResponseDTO } from '../interfaces/RoleResponseDTO';
import { AuthUserService } from '../_services/auth-user.service';
import { RoleService } from '../_services/role.service';

@Component({
  selector: 'app-user-details',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css'],
})
export class UserDetailsComponent implements OnInit {
  userId!: string;
  user!: UserResponseDTO;
  newRole: string = '';
  roles: RoleResponseDTO[] = [];
  availableRoles: RoleResponseDTO[] = [];
  rolesPage = 0;
  rolesSize = 20;
  totalRoles = 0;

  constructor(
    private route: ActivatedRoute,
    private userService: AuthUserService,
    private router: Router,
    private roleService: RoleService
  ) {}

  ngOnInit(): void {
    this.userId = this.route.snapshot.paramMap.get('id')!;
    this.loadUser();
    this.loadRoles();
  }

  loadUser() {
    this.userService.getUserById(this.userId).subscribe((data) => {
      this.user = data;
      console.log('user details loaded:', this.user);
      if (this.roles.length) {
        this.filterAvailableRoles();
      }
    });
  }

  loadRoles(page: number = 0) {
    this.roleService.getRoles(page, this.rolesSize).subscribe((data) => {
      this.roles = data.content;
      this.rolesPage = data.number;
      this.totalRoles = data.totalElements;
      console.log('user roles loaded:', this.roles);
      if (this.user) {
        this.filterAvailableRoles();
      }
    });
  }

  toggleStatus() {
    this.userService
      .updateUserStatus(this.userId)
      .subscribe(() => this.loadUser());
  }

  deleteUser() {
    Swal.fire({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, delete it!',
      cancelButtonText: 'Cancel',
      reverseButtons: true,
    }).then((result) => {
      if (result.isConfirmed) {
        this.userService.deleteUser(this.userId).subscribe(() => {
          Swal.fire('Deleted!', 'The user has been deleted.', 'success');
          this.router.navigate(['/admin/users']);
        });
      }
    });
  }

  addRole() {
    const dto: UserRoleRequestDTO = {
      username: this.user.username,
      roleName: this.newRole,
    };
    this.userService.addRoleToUser(dto).subscribe(() => {
      this.newRole = '';
      this.loadUser();
    });
  }

  removeRole(roleName: string) {
    const dto: UserRoleRequestDTO = {
      username: this.user.username,
      roleName: roleName,
    };
    this.userService.removeRoleFromUser(dto).subscribe(() => this.loadUser());
  }

  goBack() {
    this.router.navigate(['/admin/users']);
  }

  filterAvailableRoles() {
    this.availableRoles = this.roles.filter(
      (role) => !this.user.roles.includes(role.name)
    );
  }
}