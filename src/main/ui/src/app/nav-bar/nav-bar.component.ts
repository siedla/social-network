import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataService } from '../services/data.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(public userService: UserService, private dataService: DataService, private router: Router) { }

  name!: string;

  ngOnInit(): void {
  }


  search() {
    console.log(name);
    var fullName = this.name.split(' ');
    var firstName = fullName[0];
    var lastName = fullName[fullName.length - 1];
   
    this.userService.findUserByName(firstName, lastName).subscribe(data => {
      this.dataService.notifyAboutFoundUsers(data);
    });
    this.router.navigate(["foundUsers", firstName, lastName]);
  }

  logoClick() {
    this.router.navigate(['']);
  }
}
