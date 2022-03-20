import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../model/user';
import { DataService } from '../services/data.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-display-friends',
  templateUrl: './display-friends.component.html',
  styleUrls: ['./display-friends.component.css']
})
export class DisplayFriendsComponent implements OnInit {

  user: User = {} as User;
  friends: User[] = {} as User[];

  constructor(private dataService: DataService, private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.loadFriends();

  }

  loadFriends() {
    this.userService.getCurrentUser().subscribe(data => {this.user = data;
      this.userService.getFriends(this.user.id).subscribe(data => {this.friends = data; console.log(this.friends)});
    });
  }

  showProfileClick(userId: Number) {
    this.router.navigate(['profile', userId]);
  }
  showChat(userId: Number) {
    this.dataService.noifyAboutOpenedChat(userId);
  }
}
