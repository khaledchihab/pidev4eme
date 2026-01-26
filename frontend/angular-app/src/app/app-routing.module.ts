import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AboutusComponent } from './FrontComponents/aboutus/aboutus.component';
import { FormComponent } from './form/form.component';
import { BoardAdminComponent } from './boards/board-admin/board-admin.component';
import { HomeComponent } from './home/home.component';
import { HeroComponent } from './FrontComponents/hero/hero.component';
import { ProfileComponent } from './FrontComponents/profile/profile.component';
import { BoardCandidatComponent } from './boards/board-candidat/board-candidat.component';
import { DynamicformComponent } from './dynamicform/dynamicform.component';
import { TestComponent } from './test/test.component';
import { OffresDisponibleComponent } from './offres-disponible/offres-disponible.component';
import { FormDetailsComponent } from './form-details/form-details.component';
import { AddUniversityComponent } from './CreateForms/add-university/add-university.component';
import { SidePageAdminComponent } from './FrontComponents/side-page-admin/side-page-admin.component';
import { AddFormComponent } from './CreateForms/add-form/add-form.component';
import { FormfieldsComponent } from './CreateForms/formfields/formfields.component';
import { RegisterComponent } from './register/register.component';
import { FormFieldManagerComponent } from './form-field-manager/form-field-manager.component';
import { FormListComponent } from './form-list/form-list.component';
import { UserListComponent } from './user-list/user-list.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { UniversityListComponent } from './CreateForms/add-university/university-list.component';
import { ApplicationHistoryComponent } from './application-history/application-history.component';
import { AuthGuard } from './auth.guard';
import { SegmentationComponent } from './interfaces/segmentation/segmentation.component';
import { PredictionComponent } from './interfaces/prediction/prediction.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: HomeComponent },

  // All other routes are protected
  { path: 'aboutus', component: AboutusComponent, canActivate: [AuthGuard] },
  { path: 'form', component: FormComponent, canActivate: [AuthGuard]},
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
  { path: 'admin', component: BoardAdminComponent, canActivate: [AuthGuard] },
  { path: 'hero', component: HeroComponent, canActivate: [AuthGuard] },
  { path: 'user', component: BoardCandidatComponent, canActivate: [AuthGuard] },
  { path: 'dynamicform/:formId', component: DynamicformComponent, canActivate: [AuthGuard] },
  { path: 'test', component: TestComponent, canActivate: [AuthGuard] },
  { path: 'offres', component: OffresDisponibleComponent, canActivate: [AuthGuard] },
  { path: 'form-details/:formName', component: FormDetailsComponent, canActivate: [AuthGuard] },
  { path: 'addUni', component: AddUniversityComponent, canActivate: [AuthGuard] },
  { path: 'sideadmin', component: SidePageAdminComponent, canActivate: [AuthGuard] },
  { path: 'addForm', component: AddFormComponent, canActivate: [AuthGuard] },
  { path: 'addFormField', component: FormfieldsComponent, canActivate: [AuthGuard] },
  { path: 'form-field-manager/:formId', component: FormFieldManagerComponent, canActivate: [AuthGuard] },
  { path: 'form-list', component: FormListComponent, canActivate: [AuthGuard] },
  { path: 'users-list', component: UserListComponent, canActivate: [AuthGuard] },
  { path: 'user-details/:id', component: UserDetailsComponent, canActivate: [AuthGuard] },
  { path: 'university-list', component: UniversityListComponent, canActivate: [AuthGuard] },
  { path: 'application-history', component: ApplicationHistoryComponent, canActivate: [AuthGuard] },
    { path: 'segmentation', component: SegmentationComponent },
{ path: 'prediction', component: PredictionComponent },

  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
