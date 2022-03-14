import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Post } from '../model/post';
import { User } from '../model/user';
import { PostsService } from '../services/posts.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  constructor(private postService: PostsService, private userService: UserService, private route: ActivatedRoute) { }

  user: User = {} as User;
  posts: Post[] = [];
  userId!: Number;
  friendsCunter: Number = 0;

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {this.userId = params['userId']; this.loadUser(), this.loadPosts()});
  }

  
  loadUser() {
    this.userService.getUserById(this.userId).subscribe(data => {this.user = data; this.friendsCunter = data.friends.length});
  }

  loadPosts() {
    this.postService.findPostsByUserId(this.userId).subscribe(data => this.posts = data);
  }
}
