import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from '../model/post';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class PostsService {

  private url = 'http://localhost:8081/posts';

  constructor(private http: HttpClient) {
  
  }

  public getPostsBetweenId(from: number, to:number): Observable<Post[]> {
    return this.http.get<Post[]>(this.url+"/"+from+"/"+to);
  }

  public getAllPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(this.url);
  }


  public addPost(newPost: Post, userId: Number) {
    console.log(userId);
    return this.http.post<Post>(this.url+"/"+userId, newPost);
  
  }

  public updatePost(post: Post, postId: Number) {
    return this.http.put<Post>(this.url+"/"+postId, post);
  }


  public findPostsByUserId(userId: Number): Observable<Post[]> {
    return this.http.get<Post[]>(this.url+"/user/"+userId);
  }
}
