import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { AuthGuard } from './guards/auth.guard';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { UserViewComponent } from './user-view/user-view.component';

const routes: Routes = [
  {path: 'register', component: SignUpComponent},
  {path: 'login', component: SignInComponent},
  {path: '', component: UserViewComponent, canActivate: [AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
