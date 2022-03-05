import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { User } from '../model/user';
import { Observable } from 'rxjs/internal/Observable';
import { Post } from '../model/post';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private usersUrl = 'http://localhost:8081/users';

  constructor(private http: HttpClient) {
  
  }

  public findAll(): Observable<User[]> {
    return this.http.get<User[]>(this.usersUrl);
  }

  public findPostsByUserId(): Observable<Post[]> {
    return this.http.get<Post[]>(this.usersUrl+"/1/posts");
  }
}
