import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { UserAuthService } from './_services/user-auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(
    private userAuthService: UserAuthService,
    private router: Router
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    // Check if token exists AND is not expired
    if (this.userAuthService.isTokenValid()) {
      return true;
    }

    // Token missing or expired — redirect to login
    this.userAuthService.clear();
    this.router.navigate(['/login']);
    return false;
  }
}
