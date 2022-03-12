import { Component, OnInit } from '@angular/core';
import { Post } from '../model/post';
import { User } from '../model/user';
import { DataService } from '../services/data.service';
import { PostsService } from '../services/posts.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-display-friends',
  templateUrl: './display-friends.component.html',
  styleUrls: ['./display-friends.component.css']
})
export class DisplayFriendsComponent implements OnInit {

  user: User = {} as User;

  constructor(private postService: PostsService, private userService: UserService, private dataService: DataService) { }

  ngOnInit(): void {
    this.user = this.userService.getCurrentUser();
    
  }


}
