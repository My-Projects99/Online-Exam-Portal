import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) {  }

  public addUser(user:any){
    console.log("Inside userSevice",user);
    let url=`${baseUrl}/users/`;
    console.log("URL :",url);
    return this.http.post(url,user)
  }
}