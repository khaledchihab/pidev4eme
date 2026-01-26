import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthUserService } from '../_services/auth-user.service';
import { Router } from '@angular/router';
import { UserRequestDTO } from '../dto/UserRequestDTO';
import { Gender } from '../dto/enums/Gender';
import Swal from 'sweetalert2'; // Import SweetAlert2

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  constructor(
    private authUserService: AuthUserService,
    private router: Router
  ) {}

  register(registerForm: NgForm): void {
    if (registerForm.invalid) {
      Swal.fire({
        icon: 'error',
        title: 'Validation Error',
        text: 'Please fill in all required fields.',
        customClass: {
          popup: 'swal-dark'
        }
      });
      return;
    }

    const formValue = registerForm.value;
    const user: UserRequestDTO = {
      firstname: formValue.firstname,
      lastname: formValue.lastname,
      placeOfBirth: formValue.placeOfBirth,
      dateOfBirth: formValue.dateOfBirth,
      nationality: formValue.nationality,
      gender: formValue.gender as Gender,
      cin: formValue.cin,
      email: formValue.email,
      username: formValue.username,
      password: formValue.password
    };

    this.authUserService.createUser(user).subscribe({
      next: () => {
        Swal.fire({
          icon: 'success',
          title: 'Registration Successful!',
          text: 'You have been successfully registered.',
          confirmButtonText: 'OK',
          customClass: {
            popup: 'swal-dark'
          }
        }).then(() => {
          this.router.navigate(['/login']);
        });
      },
      error: (error) => {
        const errorText = error.error?.message || 'Please check your data and try again.';
        Swal.fire({
          icon: 'error',
          title: 'Registration Failed',
          text: errorText,
          customClass: {
            popup: 'swal-dark'
          }
        });
        console.error(error);
      }
    });
  }
}