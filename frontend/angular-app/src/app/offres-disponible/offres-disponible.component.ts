import { Component, OnInit } from '@angular/core';
import { DynamicServiceService } from '../_services/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-offres-disponible',
  templateUrl: './offres-disponible.component.html',
  styleUrls: ['./offres-disponible.component.css']
})
export class OffresDisponibleComponent implements OnInit {
  forms: any[] = []; // Define a property to store the list of forms

constructor(private formService: DynamicServiceService, private router: Router) { }

ngOnInit(): void {
  this.formService.getForm().subscribe((data) => {
    this.forms = data.filter((form: any) => new Date(form.datelimite) > new Date());

    // fetch avg rating for each university
    this.forms.forEach(form => {
      this.formService.getAverageRating(form.universityId).subscribe(avg => {
        form.avgRating = avg;
      });
    });
  });
}

redirectToDynamicForm(formId: number): void {
  // Redirect to the dynamic form page with the form ID as a parameter
  this.router.navigate(['/dynamicform', formId]);
}

}
