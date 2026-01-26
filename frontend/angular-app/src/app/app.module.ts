import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'; 
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './FrontComponents/navbar/navbar.component';
import { HeroComponent } from './FrontComponents/hero/hero.component';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AboutusComponent } from './FrontComponents/aboutus/aboutus.component';
import { httpInterceptorProviders } from './_helpers/http.interceptor';
import { ProfileComponent } from './FrontComponents/profile/profile.component';
import { BoardCandidatComponent } from './boards/board-candidat/board-candidat.component';
import { BoardAdminComponent } from './boards/board-admin/board-admin.component';
import { BoardUniversityComponent } from './boards/board-university/board-university.component';
import { HomeComponent } from './home/home.component';
import { SidePageAdminComponent } from './FrontComponents/side-page-admin/side-page-admin.component';
import { DynamicformComponent } from './dynamicform/dynamicform.component';
import { TestComponent } from './test/test.component';
import { DynamicCheckboxComponent } from './inputTypes/dynamic-checkbox/dynamic-checkbox.component';
import { DynamicInputComponent } from './inputTypes/dynamic-input/dynamic-input.component';
import { DynamicRadioComponent } from './inputTypes/dynamic-radio/dynamic-radio.component';
import { DynamicSelectComponent } from './inputTypes/dynamic-select/dynamic-select.component';
import { OffresDisponibleComponent } from './offres-disponible/offres-disponible.component';
import { FormDetailsComponent } from './form-details/form-details.component';
import { AddUniversityComponent } from './CreateForms/add-university/add-university.component';
import { AddFormComponent } from './CreateForms/add-form/add-form.component';
import { FormComponent } from './form/form.component';
import { FormfieldsComponent } from './CreateForms/formfields/formfields.component';
import { RegisterComponent } from './register/register.component';
import { FormFieldManagerComponent } from './form-field-manager/form-field-manager.component';
import { UniversityListComponent } from './CreateForms/add-university/university-list.component';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatTableModule } from '@angular/material/table';
import { StarRatingReadonlyComponent } from './CreateForms/add-university/star-rating-readonly.component';
import { StarRatingComponent } from './CreateForms/add-university/star-rating.component';
import { ApplicationHistoryComponent } from './application-history/application-history.component';
import { SegmentationComponent } from './interfaces/segmentation/segmentation.component';
import { PredictionComponent } from './interfaces/prediction/prediction.component';
import { AdminApplicationsComponent } from './admin-applications/admin-applications.component';
@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HeroComponent,
    FormComponent,
    LoginComponent,
    AboutusComponent,
    ProfileComponent,
    BoardCandidatComponent,
    BoardAdminComponent,
    BoardUniversityComponent,
    HomeComponent,
    SidePageAdminComponent,
    DynamicformComponent,
    TestComponent,
    DynamicCheckboxComponent,
    DynamicInputComponent,
    DynamicRadioComponent,
    DynamicSelectComponent,
    OffresDisponibleComponent,
    FormDetailsComponent,
    AddUniversityComponent,
    AddFormComponent,
    FormfieldsComponent,
    RegisterComponent,
  FormFieldManagerComponent,
  UniversityListComponent,
  StarRatingComponent,
  StarRatingReadonlyComponent,
  ApplicationHistoryComponent,
  SegmentationComponent,
  PredictionComponent,
  AdminApplicationsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatProgressSpinnerModule,
    MatTableModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
