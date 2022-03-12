import { Component, OnInit } from '@angular/core';
import { Post } from '../model/post';
import { DataService } from '../services/data.service';
import { PostsService } from '../services/posts.service';
import { Subscription } from 'rxjs';
@Component({
  selector: 'app-display-posts',
  templateUrl: './display-posts.component.html',
  styleUrls: ['./display-posts.component.css']
})
export class DisplayPostsComponent implements OnInit {

  posts: Post[] = [];
  loaded = 0;

  notifierSubscription: Subscription = this.dataService.subjectNotifier.subscribe(notified => {
      this.loadPosts();
  });

  constructor(private postService: PostsService, private dataService: DataService) { }

  ngOnInit(): void {
    this.loaded=0;
    this.loadPosts();

  }

  
  loadPosts() {
    this.postService.getPostsBetweenId(this.loaded+1, this.loaded+10).subscribe(data => {
      this.posts = data.concat(this.posts);
      this.loaded += data.length;
      console.log(this.posts);
    });

  }

  show() {
    //console.log(this.posts);
    
  }

  likeButtonClick(post: Post) {
    post.likes= post.likes + 1;
    this.postService.updatePost(post, post.id).subscribe();
  }

}
