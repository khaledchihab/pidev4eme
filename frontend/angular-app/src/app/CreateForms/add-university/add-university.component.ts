import { Component, OnInit } from '@angular/core';
import { DynamicServiceService } from 'src/app/_services/api.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-university',
  templateUrl: './add-university.component.html',
  styleUrls: ['./add-university.component.css']
})
export class AddUniversityComponent implements OnInit {
  universities: any[] = [];
  university = {
    name: '',
    location: ''
  };

  constructor(private universityService: DynamicServiceService){

  }
  ngOnInit(): void {
    this.getUniversityList();
  }
  getUniversityList() {
    this.universityService.getUniversities().subscribe(
      (response) => {
        this.universities = response;
      },
      (error) => {
        Swal.fire('Erreur', 'Erreur lors de la récupération des universités.', 'error');
      }
    );
  }
  addUniversity(university: any) {
    this.universityService.addUniversity(university).subscribe(
      (response) => {
        Swal.fire('Succès', 'Université ajoutée avec succès.', 'success');
      },
      (error) => {
        Swal.fire('Erreur', "Erreur lors de l'ajout de l'université.", 'error');
      }
    );
  }
  onSubmit() {
    this.universityService.addUniversity(this.university).subscribe(
      (response) => {
        this.universities.push(response); // Add the newly created university to the list
        this.university = { name: '', location: '' };
        Swal.fire('Succès', 'Université créée avec succès.', 'success');
      },
      (error) => {
        Swal.fire('Erreur', "Erreur lors de la création de l'université.", 'error');
      }
    );
  }

}
