import { Routes } from '@angular/router';
import { SignupComponent } from './Screens/signup/signup.component';
import { LoginComponent } from './Screens/login/login.component';
import { HomeComponent } from './Screens/home/home.component';


export const routes: Routes = [
    {path:"",component:HomeComponent,pathMatch:'full'},
    {path:"home",component:HomeComponent,pathMatch:'full'},
    {path:"login",component:LoginComponent,pathMatch:'full'},
    {path:"signup",component:SignupComponent,pathMatch:'full'}
];
