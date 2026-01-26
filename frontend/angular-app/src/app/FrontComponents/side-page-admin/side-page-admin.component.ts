import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-side-page-admin',
  templateUrl: './side-page-admin.component.html',
  styleUrls: ['./side-page-admin.component.css']
})
export class SidePageAdminComponent implements OnInit {
  isSuperAdmin = false;
  isStudent = false;
  // Add these properties to your component class
isSidebarCollapsed: boolean = false;
isLoggedIn: boolean = false; // Your existing property



  ngOnInit(): void {
    const userStr = localStorage.getItem('User');
    if (userStr) {
      const user = JSON.parse(userStr);
      const roles: string[] = user.roles || [];
      this.isSuperAdmin = roles.includes('SUPER_ADMIN');
      this.isStudent = roles.includes('STUDENT');
    }
  }
  // Add this method to toggle the sidebar
toggleSidebar(): void {
  this.isSidebarCollapsed = !this.isSidebarCollapsed;
}
}
