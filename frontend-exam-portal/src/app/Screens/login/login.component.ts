import { Component } from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import Swal from 'sweetalert2'
import { Router } from '@angular/router'; // ✅ add this import
import {MatCardModule} from '@angular/material/card';// used to make form as a card
import { LoginService } from './../../services/login.service';
import { error, log } from 'console';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [    
    RouterModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    MatCardModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  constructor(
    private snackBar: MatSnackBar,
    private router: Router ,// ✅ injected here
    private login:LoginService
  ){}

  public userInfo={
    username:'',
    password:'',
  }

  formSubmit(){
    if(this.userInfo.username.trim()=='' || this.userInfo.username==null){  //.trim() here used for when we input only space in the text area then it remove space and show it as a empty field.
      this.snackBar.open('Input Valid UserName !!', 'Close', {duration: 2000,verticalPosition:'top',horizontalPosition:'center'});
      return;
    }
    else if(this.userInfo.password.trim()=='' || this.userInfo.password==null){
      this.snackBar.open('Input Valid Password !!', 'Close', {duration: 2000,verticalPosition:'top',horizontalPosition:'center'});
      return;
    }
    else{
      console.log(this.userInfo)
      //Request to server to generate token
      this.login.generateToken(this.userInfo).subscribe(
      (data:any) =>{
        console.log('success');
        console.log(data);
        //token store in localStorage 
        this.login.loginUser(data.token);

        //get the login user Details 
        this.login.getCurrentUser().subscribe(
          (user:any)=>{
            console.log(user)
            //user save on localStorage 
            this.login.setUser(user);

            //Redirect if ADMIN :admin dashboard else normal user :normaluser dashboard
            if(this.login.getUserRole()=="ADMIN"){
              //Redirect to Admin page 
              window.location.href='/admindash'
              // this.login.loginStatusSubject.next(true);
            }else if(this.login.getUserRole()=="STUDENT"){
              //Redirect to Student page
              window.location.href='/userdash'
              // this.login.loginStatusSubject.next(true);
            }else{
              this.login.logout();
            }
          },
          (error)=>{

          }
        )

      },
    (error) =>{
      console.log("Error !");
      console.log(error);
    });
      alert("login success")
    }
    
  }

  clearForm() {
    this.userInfo = {
      username: "",
      password: ""   
    };
    // this.router.navigate(['/']); // ✅ navigate to login
  }
}
