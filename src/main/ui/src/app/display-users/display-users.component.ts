import { Component, OnInit } from '@angular/core';
import { Post } from '../model/post';
import { User } from '../model/user';
import { UserService } from '../services/user.service';


@Component({
  selector: 'app-display-users',
  templateUrl: './display-users.component.html',
  styleUrls: ['./display-users.component.css']
})
export class DisplayUsersComponent implements OnInit  {

  constructor(private userService: UserService) { }

  users: User[] = [];
  posts: Post[] = [];
  userCount = 0;


  ngOnInit() {
	  this.getAllUsers();
    this.getPostsByUserId();
    
  }

  getAllUsers() {
    this.userService.findAll().subscribe(data => {
      this.users = data;
    });
  }

  getPostsByUserId() {
    this.userService.findPostsByUserId().subscribe(data => {
      this.posts = data;
    });

  }
}
