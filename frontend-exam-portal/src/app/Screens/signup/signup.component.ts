import { Component, OnInit } from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { Console, error } from 'console';
import { UserService } from '../../services/user.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ToastrService } from 'ngx-toastr';
import Swal from 'sweetalert2'
import { RouterModule } from '@angular/router';
import { Router } from '@angular/router'; // ✅ add this import
import {MatCardModule} from '@angular/material/card';// used to make form as a card

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [    
    RouterModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    MatCardModule
  ],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {

  constructor(private userService: UserService,
    private snackBar: MatSnackBar,
    private toastr: ToastrService,
    private router: Router // ✅ injected here
  ) {}  // Inject service

  public user={
    username:"",
    firstName:"",
    lastName:"",
    password:"",
    email:"",
    phone:""
  }
  // 👉 Email validation using regex
  validateEmail(email: string): boolean {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/i;
    return emailRegex.test(email);
  }

  formSubmit(){
    console.log(this.user);
    if(this.user.username==''||this.user.username==null){
      this.snackBar.open('User Name is Unique Required !!', 'Close', {duration: 2000,verticalPosition:'top',horizontalPosition:'center'});
      return
    }
    else if(this.user.firstName==''||this.user.firstName==null){
      // this.toastr.warning('firstName is Required !!', 'Ok');
      this.snackBar.open('First Name is Required !!', 'Close', {duration: 2000,verticalPosition:'top',horizontalPosition:'center'});
      return
    }
    else if(this.user.lastName==''||this.user.lastName==null){
      this.snackBar.open('Last Name is Required !!', 'Close', {duration: 2000,verticalPosition:'top',horizontalPosition:'center'});
      return
    }
    else if(this.user.password==''||this.user.password==null){
      this.snackBar.open('password is Required !!', 'Close', {duration: 2000,verticalPosition:'top',horizontalPosition:'center'});
      return
    }
    else if(this.user.email==''||this.user.email==null){
      this.snackBar.open('Email is Required !!', 'Close', {duration: 2000,verticalPosition:'top',horizontalPosition:'center'});
      return
    }
    // Email validation using
    else if (!this.validateEmail(this.user.email)) {
      this.snackBar.open('Invalid Email Format !!', 'Close', { duration: 2000, verticalPosition: 'top',horizontalPosition: 'center'});
      return;
    }
    
    else if(this.user.phone==''||this.user.phone==null){
      this.snackBar.open('phone is Required !!', 'Close', {duration: 2000,verticalPosition:'top',horizontalPosition:'center'});
      return
    }
    // Check phone number format (must be 10 digits)
    if (!/^\d{10}$/.test(this.user.phone)) {
      this.snackBar.open('Phone number must be exactly 10 digits!', 'Close', {duration: 2000,verticalPosition: 'top',horizontalPosition: 'center'});
      return;
}

    else{
    this.userService.addUser(this.user).subscribe(
      (data)=>{
        //success
        console.log(data);
        Swal.fire('success','Registerd Successfully ','success')
        // alert("Success");
        this.router.navigate(['/login']); // ✅ navigate to login
      },
      (error)=>{
        //error
        console.log(error);
        Swal.fire('Error','Somthing went wrong !! User Already Register !! username should be Uniq','error')
        // alert("Somthing went wrong !!")
      }
    )
    // alert("form Submited");
  }
  
  
  }

  clearForm() {
    this.user = {
      username: "",
      firstName: "",
      lastName: "",
      password: "",
      email: "",
      phone: ""
    };
  }
  
}
