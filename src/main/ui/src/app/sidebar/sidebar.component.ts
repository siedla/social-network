import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../model/user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  constructor(private userService: UserService, private route: Router) { }

  user: User = {} as User;

  ngOnInit(): void {
    this.userService.getCurrentUser().subscribe(data => this.user=data);
  }

  logOutClick() {
    this.userService.logOut();
    this.route.navigate(['login']);
  }

  showProfileClick() {
    this.route.navigate(['profile', this.user.id]);
  }
}
