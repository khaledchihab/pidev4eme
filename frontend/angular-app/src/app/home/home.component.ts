import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  content?: string;

  constructor(private userService: UserService) { }
  ngOnInit(): void {
    this.userService.getPublicContent().subscribe({
      next: data => {
        this.content = data;
      },
      error: err => {
        console.log(err);
        if (typeof err?.error === 'string') {
          try {
            this.content = JSON.parse(err.error).message;
          } catch {
            this.content = err.error;
          }
        } else if (err?.error?.message) {
          this.content = err.error.message;
        } else {
          this.content = "Error with status: " + err.status;
        }
      }
    });
  }

}
