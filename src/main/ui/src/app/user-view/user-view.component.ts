import { Component, HostListener, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Post } from '../model/post';
import { DataService } from '../services/data.service';
import { PostsService } from '../services/posts.service';

@Component({
  selector: 'app-user-view',
  templateUrl: './user-view.component.html',
  styleUrls: ['./user-view.component.css']
})
export class UserViewComponent implements OnInit {

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
    this.postService.getAllPosts().subscribe(data => {
      this.posts = data;
      this.loaded += data.length;
    
    });
  }

}
