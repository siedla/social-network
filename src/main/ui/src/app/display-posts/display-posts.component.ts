import { Component, Input, OnInit } from '@angular/core';
import { Post } from '../model/post';
import { DataService } from '../services/data.service';
import { PostsService } from '../services/posts.service';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';
@Component({
  selector: 'app-display-posts',
  templateUrl: './display-posts.component.html',
  styleUrls: ['./display-posts.component.css']
})
export class DisplayPostsComponent implements OnInit {

  @Input() posts: Post[] = [];

  constructor(private postService: PostsService, private router: Router) { }

  ngOnInit(): void {


  }


  likeButtonClick(post: Post) {
    post.likes= post.likes + 1;
    this.postService.updatePost(post, post.id).subscribe();
  }

  showProfileClick(userId: Number) {
    this.router.navigate(['profile', userId]);
  }
}
