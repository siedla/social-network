import { Component, OnInit } from '@angular/core';
import { Post } from '../model/post';
import { User } from '../model/user';
import { PostsService } from '../services/posts.service';
import { UserService } from '../services/user.service';
import { DataService } from '../services/data.service';
@Component({
  selector: 'app-post-adding-form',
  templateUrl: './post-adding-form.component.html',
  styleUrls: ['./post-adding-form.component.css']
})
export class PostAddingFormComponent implements OnInit {

  newPost: Post={} as Post;
  email: string = "";
  user: User = {} as User;

  shortLink: string = "";
  loading: boolean = false; // Flag variable
  file!: File;

  constructor(private postService: PostsService, private userService: UserService, private dataService: DataService) { }

  ngOnInit(): void {
  }

  onSubmit()  {
  
    this.userService.getCurrentUser().subscribe(data=> {
      this.user = data;
      this.newPost.user = this.user;
      this.newPost.likes=0;
      this.postService.addPost(this.newPost, this.user.id).subscribe(res => this.dataService.notifyAboutChange());
      
      this.newPost.description="";});
    
    
  }


 
}
