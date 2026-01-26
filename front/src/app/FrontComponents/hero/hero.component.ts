import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthUserService } from 'src/app/_services/auth-user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-hero',
  templateUrl: './hero.component.html',
  styleUrls: ['./hero.component.css']
})
export class HeroComponent implements OnInit {

isLoggedIn = false;

  constructor(private authUserService: AuthUserService, private router: Router) {}

  ngOnInit(): void {
    this.isLoggedIn = this.authUserService.isLoggedIn();
  }

  goToUniversities() {
    if (this.isLoggedIn) {
      this.router.navigate(['/university-list']);
    } else {
      Swal.fire({
        icon: 'warning',
        title: 'Connexion requise',
        text: 'Vous devez vous connecter pour consulter les universités.',
        confirmButtonText: 'Se connecter'
      }).then((result) => {
        if (result.isConfirmed) {
          this.router.navigate(['/login']);
        }
      });
    }
  }

  goToForms() {
    if (this.isLoggedIn) {
      this.router.navigate(['/offres']);
    } else {
      Swal.fire({
        icon: 'warning',
        title: 'Connexion requise',
        text: 'Vous devez vous connecter pour consulter les formulaires.',
        confirmButtonText: 'Se connecter'
      }).then((result) => {
        if (result.isConfirmed) {
          this.router.navigate(['/login']);
        }
      });
    }
  }
}

