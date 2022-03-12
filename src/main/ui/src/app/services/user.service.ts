import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { User } from '../model/user';
import { Observable } from 'rxjs/internal/Observable';
import { Post } from '../model/post';
import { BehaviorSubject, map, Subject } from 'rxjs';

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

  //dokonczyc
  public findPostsByUserId(): Observable<Post[]> {
    return this.http.get<Post[]>(this.usersUrl+"/1/posts");
  }

  public getUserByEmail(email: String): Observable<User> {
    return this.http.get<User>(this.usersUrl+"/email/"+email);
  }

  public findUserByName(firstName: string, lastName: string): Observable<User[]> {
    return this.http.get<User[]>(this.usersUrl+"/"+firstName+"/"+lastName);
  }

  public addNewUser(user: User) {
    return this.http.post<User>('http://localhost:8081/signUp', user);
  }


  public authenticate(email: string, password: string) {
    return this.http
      .post<any>("http://localhost:8081/authenticate", { email, password })
      .pipe(
        map(userData => {
          sessionStorage.setItem("email", email);
          sessionStorage.setItem("user", JSON.stringify(userData.user));
  
          let tokenStr = "Bearer " + userData.token;
          sessionStorage.setItem("token", tokenStr);
          return userData;
        })
      );
  }

  public isUserLoggedIn() {
    let user = sessionStorage.getItem("email");
    return !(user === null);
  }

  public logOut() {
    sessionStorage.removeItem("email");
    sessionStorage.removeItem("user");
  }

  public getCurrentUser(){
    let currentUser:User = JSON.parse(sessionStorage.getItem("user")!) as User;
    return currentUser;
    
  }
}
