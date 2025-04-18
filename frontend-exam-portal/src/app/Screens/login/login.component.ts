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
    private router: Router // ✅ injected here
  ){}

  public userInfo={
    userName:'',
    password:'',
  }

  formSubmit(){
    if(this.userInfo.userName=='' || this.userInfo.userName==null){
      this.snackBar.open('Input Valid UserName !!', 'Close', {duration: 2000,verticalPosition:'top',horizontalPosition:'center'});
      return;
    }
    else if(this.userInfo.password=='' || this.userInfo.password==null){
      this.snackBar.open('Input Valid Password !!', 'Close', {duration: 2000,verticalPosition:'top',horizontalPosition:'center'});
      return;
    }
    else{
      console.log(this.userInfo)
      alert("login success")
    }
    
  }

  clearForm() {
    this.userInfo = {
      userName: "",
      password: ""   
    };
    // this.router.navigate(['/']); // ✅ navigate to login
  }
}
