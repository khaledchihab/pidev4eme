import { Component, OnInit } from '@angular/core';
import { DynamicServiceService } from 'src/app/_services/api.service';
import { AuthUserService } from 'src/app/_services/auth-user.service';
import { UniversityService } from 'src/app/_services/university.service';
import { UserResponseDTO } from 'src/app/dto/UserResponseDTO';

@Component({
  selector: 'app-university-list',
  templateUrl: './university-list.component.html',
  styleUrls: ['./university-list.component.css']
})
export class UniversityListComponent implements OnInit {
  universities: any[] = [];
  userId!: string;

  constructor(
    private universityService: DynamicServiceService,
    private userService: AuthUserService,
    private service: UniversityService
  ) {}

  ngOnInit(): void {
    const username = localStorage.getItem('Username');
    if (username) {
      // Fetch the logged-in user's ID first
      this.userService.getUserByUsername(username).subscribe({
        next: (user: UserResponseDTO) => {
          this.userId = user.id;
          console.log('User ID:', this.userId);
          // Only fetch universities after we have the user ID
          this.getUniversityList();
        },
        error: (err) => console.error('Error fetching user', err)
      });
    } else {
      // If no user is logged in, still fetch universities
      this.getUniversityList();
    }
  }

  getUniversityList() {
    // Call backend endpoint that includes avgRating
    this.service.getUniversitiesWithAvgRating().subscribe(
      (response: any[]) => {
        this.universities = response.map(uni => ({
          ...uni,
          isExpanded: false,
          avgRating: uni.avgRating || 0
        }));
        console.log('Universities fetched successfully:', this.universities);
      },
      (error) => {
        console.error('Error fetching universities:', error);
      }
    );
  }

  onRate(university: any, rating: number) {
    if (!this.userId) {
      console.error('User ID not found. Cannot rate.');
      return;
    }

    this.universityService.rateUniversity(university.universityId, this.userId, rating)
      .subscribe(() => {
        // Refresh average rating after rating
        this.universityService.getAverageRating(university.universityId).subscribe(avg => {
          university.avgRating = avg;
        });
      });
  }

  toggleForms(university: any) {
    // Collapse all other expanded universities
    this.universities.forEach(uni => {
      if (uni !== university && uni.isExpanded) {
        uni.isExpanded = false;
      }
    });
    // Toggle clicked university
    university.isExpanded = !university.isExpanded;
  }
}
