import { Component, OnInit } from '@angular/core';
import { Post } from '../model/post';
import { User } from '../model/user';
import { PostsService } from '../services/posts.service';
import { UserService } from '../services/user.service';
import { FormsModule } from '@angular/forms';
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

  constructor(private postService: PostsService, private userService: UserService, private dataService: DataService) { }

  ngOnInit(): void {
  }

  onSubmit()  {
  
    this.user = this.userService.getCurrentUser();
    this.newPost.user = this.user;
    this.postService.addPost(this.newPost, this.user.id).subscribe(res => this.dataService.notifyAboutChange());
    
    this.newPost.description="";
      
  }


 
}
