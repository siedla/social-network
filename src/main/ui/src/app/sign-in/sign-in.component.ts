import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { User } from '../model/user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {

  constructor(private router: Router, private userService: UserService) { }

  ngOnInit(): void {
  }

 form = new FormGroup({  
    email : new FormControl('' , Validators.required),  
    password : new FormControl('' , Validators.required)  
  });  
  
//   notifierSubscription: Subscription = this.userService.loggedIn.subscribe(data => {
//     this.router.navigate(['/user-view']);
// });

  login() {
    let user: User = {} as User;
    user.email = this.Email;
    user.password = this.Password;
    this.userService.authenticate(this.Email, this.Password).subscribe(data => {
      this.router.navigate(['']);
    });
  }
  
  get Email(){  
      return this.form.controls['email'].value;  
  }  
  
  get Password(){  
      return this.form.controls['password'].value;  
  }  
}
