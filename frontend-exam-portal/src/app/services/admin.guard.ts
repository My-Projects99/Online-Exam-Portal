// import { CanActivateFn } from '@angular/router';

// export const adminGuard: CanActivateFn = (route, state) => {

//   return true;
// };
// src/app/guards/admin.guard.ts

// import { CanActivateFn, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
// import { inject } from '@angular/core';
// import { LoginService } from '../services/login.service'; // Update path as needed

// export const adminGuard: CanActivateFn = (
//   route: ActivatedRouteSnapshot,
//   state: RouterStateSnapshot
// ) => {
//   const loginService = inject(LoginService);
//   const isLoggedIn = loginService.isLoggedIn();
  
//   // Optionally check for admin role if needed
//   // const user = loginService.getUser();
//   // if (user.role !== 'ADMIN') return false;

//   return isLoggedIn;
// };

import { CanActivateFn, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { inject } from '@angular/core';
import { LoginService } from '../services/login.service'; // adjust path as needed

export const adminGuard: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
): boolean | UrlTree => {
  const loginService = inject(LoginService);
  const router = inject(Router);

  if (loginService.isLoggedIn()) {
    return true;
  } else {
    // Redirect to login page
    return router.parseUrl('/login');
  }
};


