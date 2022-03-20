import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms'; 
import { Router } from '@angular/router';
import { User } from '../model/user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  constructor(private userService: UserService, private router: Router) {
    if(this.userService.isUserLoggedIn()){
      this.router.navigate(['']);
    }
   }

  ngOnInit(): void {
  }

  form = new FormGroup({  
    firstName : new FormControl('' , Validators.required), 
    lastName : new FormControl('' , Validators.required),  
    email : new FormControl('' , Validators.required),  
    password : new FormControl('' , Validators.required),  
    confirmPassword : new FormControl('' , Validators.required),  
});  

  submit() {

    let newUser: User = {} as User;
    newUser.firstName=this.FirstName;
    newUser.lastName=this.LastName;
    newUser.email=this.Email;
    newUser.password=this.Password;
    this.userService.addNewUser(newUser).subscribe(t => this.router.navigate(['']));
  }

  get FirstName(){  
    return this.form.controls['firstName'].value;  
  }  
  
  get LastName(){  
    return this.form.controls['lastName'].value;  
  }  

  get Email(){  
      return this.form.controls['email'].value;  
  }  

  get Password(){  
      return this.form.controls['password'].value;  
  }  

  get ConfirmPassword(){  
      return this.form.controls['confirmPassword'].value;  
  }  

}
