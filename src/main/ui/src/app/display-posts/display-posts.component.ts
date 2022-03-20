import { Component, Input, OnInit } from '@angular/core';
import { Post } from '../model/post';
import { DataService } from '../services/data.service';
import { PostsService } from '../services/posts.service';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { User } from '../model/user';
@Component({
  selector: 'app-display-posts',
  templateUrl: './display-posts.component.html',
  styleUrls: ['./display-posts.component.css']
})
export class DisplayPostsComponent implements OnInit {

  @Input() posts: Post[] = [];

  constructor(private postService: PostsService, private router: Router, private userSerivce: UserService) { }

  user: User = {} as User;
  ngOnInit(): void {
    this.userSerivce.getCurrentUser().subscribe(data => this.user = data);
    
  }


  likeButtonClick(post: Post) {
    var alreadyLike = this.checkIfAlreadyLike(post);
    
    if(!alreadyLike){
      post.likes= post.likes + 1;
      post.likedBy.push(this.user);
      this.postService.likePost(post, post.id, this.user.id).subscribe(data => post = data);
    }
    else {
      post.likes= post.likes - 1;
      const index: number = post.likedBy.indexOf(this.user);
      post.likedBy.splice(index, 1);
      this.postService.dislikePost(post, post.id, this.user.id).subscribe(data => post = data);
    }
   
  }

  showProfileClick(userId: Number) {
    this.router.navigate(['profile', userId]);
  }

  checkIfAlreadyLike(post: Post) {
    var alreadyLike = false;
    for(var i=0; i<post.likedBy.length; i++){
      if(post.likedBy[i].id == this.user.id){
          alreadyLike = true;
          break;
      }
    }
    if(alreadyLike) return true;
    return false;
  }
}
