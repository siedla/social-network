import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { DisplayUsersComponent } from './display-users/display-users.component';
import { AuthGuard } from './guards/auth.guard';
import { DisplayFoundUsersComponent } from './search/display-found-users/display-found-users.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { UserViewComponent } from './user-view/user-view.component';

const routes: Routes = [
  {path: 'register', component: SignUpComponent},
  {path: 'login', component: SignInComponent},
  {path: '', component: UserViewComponent, canActivate: [AuthGuard]},
  {path: 'foundUsers', component: DisplayFoundUsersComponent, canActivate: [AuthGuard]},
  {path: 'display', component: DisplayUsersComponent},
  {path: 'profile/:userId', component: UserProfileComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
