import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    
    const token = localStorage.getItem('Token'); // or use a real AuthService

    if (token) {
      return true; // ✅ User is logged in
    } else {
      // ❌ Not logged in → redirect to login
      this.router.navigate(['/login']);
      return false;
    }
  }
}
