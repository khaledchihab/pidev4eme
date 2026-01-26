import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms'; // Import the necessary modules
import { DynamicServiceService } from 'src/app/_services/api.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-add-form',
  templateUrl: './add-form.component.html',
  styleUrls: ['./add-form.component.css']
})
export class AddFormComponent implements OnInit {
  universities: any[] = [];
  forms: any[] = [];
  sortedForms: any[] = [];
  sortField: string = '';
  isDesc: boolean = false;
  form: FormGroup = new FormGroup({}); // Initialize it with an empty FormGroup
  constructor(
    private apiService: DynamicServiceService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    // Fetch universities and populate the dropdown
    this.apiService.getUniversities().subscribe((data: any[]) => {
      this.universities = data;
    });

    // Create the form with validation
    this.form = this.formBuilder.group({
      universityId: ['', Validators.required], // Dropdown control with required validation
      formName: ['', Validators.required],
      description: ['', Validators.required],
      placesDisp: ['', [Validators.required, Validators.min(1)]], // Add Validators.min(1) to enforce a minimum value of 1
      datelimite: ['', Validators.required]
    });

    this.apiService.getForm().subscribe(
      (response) => {
        this.forms = response; // Assign the API response to the forms property
      },
      (error) => {
        console.error('Error fetching forms:', error);
      }
    );
    this.loadForms();

  }
  getFieldValue(item: any, field: string): any {
    if (field === 'datelimite') {
      return new Date(item[field]);
    } else if (field === 'placesDisp') {
      // Parse placesDisp as a number for numerical sorting
      return parseFloat(item[field]);
    }
    return item[field].toLowerCase();
  }
  loadForms() {
    this.apiService.getForm().subscribe(
      (response) => {
        this.forms = response;
        this.sortedForms = [...this.forms];
      },
      (error) => {
        console.error('Error fetching forms:', error);
      }
    );
  }

  sortTable(field: string) {
    if (this.sortField === field) {
      this.isDesc = !this.isDesc;
    } else {
      this.sortField = field;
      this.isDesc = false;
    }

    this.sortedForms = [...this.forms].sort((a, b) => {
      const aValue = this.getFieldValue(a, field);
      const bValue = this.getFieldValue(b, field);
      if (aValue < bValue) return this.isDesc ? 1 : -1;
      if (aValue > bValue) return this.isDesc ? -1 : 1;
      return 0;
    });
  }

  // Define a function to submit the form
  onSubmit() {
    if (this.form.valid) {
      const formDTO = this.form.value;
      this.apiService.addForm(formDTO).subscribe({
        next: (response) => {
          Swal.fire('Succès', 'Formulaire ajouté avec succès.', 'success');
        },
        error: (error) => {
          Swal.fire('Erreur', "Erreur lors de l'ajout du formulaire.", 'error');
        }
      });
    } else {
      Swal.fire('Erreur', 'Veuillez remplir correctement tous les champs du formulaire.', 'error');
    }
  }

}
