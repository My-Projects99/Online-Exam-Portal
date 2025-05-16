import { Component, OnInit } from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar'; // ✅ import this
import { LoginService } from '../../services/login.service';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [    
    CommonModule, // 👈 Add this
    RouterModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    MatIconModule,
    MatToolbarModule
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit {

  constructor( public login:LoginService,private router:Router) {}

  isLoggedIn=false;
  user=null;
  ngOnInit(): void {
    this.isLoggedIn=this.login.isLoggedIn();
    this.user=this.login.getUser();
    this.login.loginStatusSubject.asObservable().subscribe(data=>{
        this.isLoggedIn=this.login.isLoggedIn();
        this.user=this.login.getUser();
    });
      
  }
//   export class NavbarComponent{
//   /**
//    *
//    */
//   constructor( public login:LoginService,private router:Router) {}

  public logout(){
    this.login.logout();
    // this.router.navigate(['/home'])
    window.location.href='/home'
  }

}
