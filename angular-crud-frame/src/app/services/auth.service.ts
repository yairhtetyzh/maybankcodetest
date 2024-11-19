import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from './../services/user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private isAuthenticated = false;
  private authSecretKey = 'Bearer Token';

  constructor(private router: Router, private userService: UserService) { 
    this.isAuthenticated = !!localStorage.getItem(this.authSecretKey);
  }
  
  login(data: any): boolean {
      localStorage.setItem(this.authSecretKey, data.token);
      this.isAuthenticated = true;
      console.log("success");
      return true;
  }

  isAuthenticatedUser(): boolean {
    return this.isAuthenticated;
  }

  logout(): void {
    localStorage.removeItem(this.authSecretKey);
    this.isAuthenticated = false;
    this.router.navigate(['login']);
  }

  getToken(): any {
    if(this.isAuthenticated)
    return localStorage.getItem(this.authSecretKey);
    else return null;
  }
}
