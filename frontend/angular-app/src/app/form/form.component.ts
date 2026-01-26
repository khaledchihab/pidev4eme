import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent {
  formData: any = {
    genre: '', // Initialize with an empty string
    departement: '' // Initialize with an empty string
  };
  selectedFile: File | null = null;
  constructor(private http: HttpClient){}

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  // onSubmit() {
  //   if (this.selectedFile) {
  //     const formData = new FormData();
  //     formData.append('pdfFile', this.selectedFile);
  
  //     this.http.post('http://localhost:8090/upload', formData).subscribe(
  //       (response) => {
  //         console.log('File uploaded successfully.', response);
  //         // Handle the server's response as needed.
  //       },
  //       (error) => {
  //         console.error('Error uploading file:', error);
  //       }
  //     );
  //   } else {
  //     console.log('No file selected.');
  //   }
  // }
  onSubmit(formData: any) {
    // Process or save the form data here
    console.log('Form data submitted:', formData);

    // Create a FormData object to handle file uploads
    const uploadData = new FormData();
    uploadData.append('nom', formData.nom);
    uploadData.append('email', formData.email);
    uploadData.append('genre', formData.genre);
    uploadData.append('daten', formData.daten);
    uploadData.append('idesprit', formData.idesprit);
    uploadData.append('classe', formData.classe);
    uploadData.append('departement', formData.departement);
    uploadData.append('moy3', formData.moy3);
    uploadData.append('moy2', formData.moy2);
    uploadData.append('moy1', formData.moy1);
    uploadData.append('anglais', formData.anglais);
    uploadData.append('pdfFile', formData.pdfFile);

    // Now, uploadData contains all form data, including the uploaded file
    console.log('this is the object:',uploadData)

    // You can send the data to an API or perform any other necessary file handling.
  }

}
