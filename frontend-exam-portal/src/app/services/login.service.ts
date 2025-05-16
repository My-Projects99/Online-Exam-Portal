import { HttpClient } from '@angular/common/http';
import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { Subject } from 'rxjs';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private isBrowser: boolean;

  constructor(
    private http: HttpClient,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {
    this.isBrowser = isPlatformBrowser(this.platformId);
  }

  public loginStatusSubject = new Subject<boolean>();

  // Generate token 
  public generateToken(userInfo: any) {
    return this.http.post(`${baseUrl}/generate-token`, userInfo);
  }

  // Get current user
  public getCurrentUser() {
    return this.http.get(`${baseUrl}/current-user`);
  }

  // Login user: store token
  public loginUser(token: any) {
    if (this.isBrowser) {
      localStorage.setItem('token', token);
    }
    return true;
  }

  // Check login status
  public isLoggedIn(): boolean {
    return this.isBrowser && !!localStorage.getItem('token');
  }

  // Logout user
  public logout() {
    if (this.isBrowser) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
    }
    return true;
  }

  // Get token
  public getToken() {
    if (this.isBrowser) {
      return localStorage.getItem('token');
    }
    return null;
  }

  // Set user details
  public setUser(user: any) {
    if (this.isBrowser) {
      localStorage.setItem('user', JSON.stringify(user));
    }
  }

  // Get user details
  public getUser() {
    if (this.isBrowser) {
      let userStr = localStorage.getItem('user');
      if (userStr != null) {
        return JSON.parse(userStr);
      } else {
        this.logout();
        return null;
      }
    }
    return null;
  }

  // Get user role
  public getUserRole() {
    const user = this.getUser();
    return user?.authorities[0]?.authority || null;
  }
}




// import { HttpClient } from '@angular/common/http';
// import { Injectable } from '@angular/core';
// import baseUrl from './helper';
// import { Subject } from 'rxjs';

// @Injectable({
//   providedIn: 'root'
// })
// export class LoginService {

//   constructor(private http:HttpClient) { }

//   public loginStatusSubject=new Subject<boolean>();

//   //generate token 
//   public generateToken(userInfo:any){
//     return this.http.post(`${baseUrl}/generate-token`,userInfo);
//   }

//   //get current User: which is login
//   public getCurrentUser(){
//     return this.http.get(`${baseUrl}/current-user`)
//   }

//   //login user : set token in LocalStorage
//   public loginUser(token : any){
//     localStorage.setItem('token',token);
//     return true;
//   }
//   //isLogin: user is Logged in or Not
//   public isLoggedIn(): boolean {
//   if (typeof window !== 'undefined' && localStorage.getItem("token")) {
//     return true;
//   }
//   return false;
// }
//   // public isLoggedIn():boolean{
//   //   let tokenstr=localStorage.getItem("token");
//   //   if(tokenstr==undefined || tokenstr=='' || tokenstr==null){
//   //     return false;
//   //   }
//   //   else{
//   //     return true;
//   //   }
//   // }

//   //Logout : remove token from localStorage
//   public logout(){
//     localStorage.removeItem('token');
//     localStorage.removeItem('user');
//     return true;
//   }

//   // get token 
//   public getToken(){
//     return localStorage.getItem('token');
//   }

//   //set user Details
//   public setUser(user : any){
//     localStorage.setItem('user',JSON.stringify(user));
//   }
//   //get user
//   public getUser(){
//     let userstr=localStorage.getItem("user");
//     if(userstr!=null){
//       return JSON.parse(userstr);
//     }else{
//       this.logout();
//       return null;
//     }

//   }

//   //get user role 
//   public getUserRole(){
//     let user=this.getUser();
//     return user.authorities[0].authority;
//   }

// }
