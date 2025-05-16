import { Routes } from '@angular/router';
import { SignupComponent } from './Screens/signup/signup.component';
import { LoginComponent } from './Screens/login/login.component';
import { HomeComponent } from './Screens/home/home.component';
import { AdminDashboardComponent } from './Screens/admin/admin-dashboard/admin-dashboard.component';
import { UserDashboardComponent } from './Screens/user/user-dashboard/user-dashboard.component';
import { ProfileComponent } from './Screens/profile/profile.component';
import { adminGuard } from './services/admin.guard';
import { WelcomeComponent } from './Screens/admin/welcome/welcome.component';


export const routes: Routes = [
    {path:"",component:HomeComponent,pathMatch:'full'},
    {path:"home",component:HomeComponent,pathMatch:'full'},
    {path:"login",component:LoginComponent,pathMatch:'full'},
    {path:"signup",component:SignupComponent,pathMatch:'full'},
    {
        path:"admindash",component:AdminDashboardComponent,canActivate:[adminGuard],
        children:[
            { path:'profile',component:ProfileComponent},
            { path:'',component:WelcomeComponent},
        ]
    },
    {path:"userdash",component:UserDashboardComponent,pathMatch:'full'}
];
