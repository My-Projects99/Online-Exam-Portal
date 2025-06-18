import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { RouterModule } from '@angular/router';
import { MatTableModule } from '@angular/material/table';
import { LoginService } from '../../services/login.service';

export interface User {
  id: number;
  username: string;
  firstName: string;
  lastName: string;
  phone: string;
  email: string;
  enabled: boolean;
  authorities: { authority: string }[];
  profile: string;
}

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [    
    RouterModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    MatCardModule,
    MatTableModule
  ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit {

  // user= null; 
    user: User | null = null;

  constructor(private login: LoginService) {}

  ngOnInit(): void {
    this.user = this.login.getUser();
    console.log("Loaded user:", this.user);
  }
  
}


// import { Component, OnInit } from '@angular/core';
// import { FormsModule } from '@angular/forms';
// import { MatButtonModule } from '@angular/material/button';
// import { MatCardModule } from '@angular/material/card';
// import { MatFormFieldModule } from '@angular/material/form-field';
// import { MatInputModule } from '@angular/material/input';
// import { RouterModule } from '@angular/router';
// import {MatTableModule} from '@angular/material/table';
// import { LoginService } from '../../services/login.service';
// import { error } from 'console';

// @Component({
//   selector: 'app-profile',
//   standalone: true,
//   imports: [    
//     RouterModule,
//     MatButtonModule,
//     MatInputModule,
//     MatFormFieldModule,
//     FormsModule,
//     MatCardModule,
//     MatTableModule
//   ],
//   templateUrl: './profile.component.html',
//   styleUrl: './profile.component.css'
// })
// export class ProfileComponent implements OnInit {
  
//   user=null;
//   constructor(private login:LoginService) {}

//   ngOnInit(): void {
//       // this.user=this.login.getUser();
//       // console.log("Loaded user:", this.user);
//       this.login.getCurrentUser().subscribe(
//         (user :any)=>{
//           this.user=user;
//         },
//         (error)=>{
//           console.log(error);
//         }
//       )
//   }
//   /**
//    *
//    */

// }
