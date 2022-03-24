import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { User } from '../model/user';
import { Observable } from 'rxjs/internal/Observable';
import { map } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private usersUrl = environment.baseUrl+"users";

  constructor(private http: HttpClient) {

  }

  public findAll(): Observable<User[]> {
    return this.http.get<User[]>(this.usersUrl);
  }

  public getUserByEmail(email: String): Observable<User> {
    return this.http.get<User>(this.usersUrl+"/email/"+email);
  }

  public getUserById(id: Number): Observable<User> {
    return this.http.get<User>(this.usersUrl+"/"+id);
  }

  public findUserByName(firstName: string, lastName: string): Observable<User[]> {
    return this.http.get<User[]>(this.usersUrl+"/"+firstName+"/"+lastName);
  }

  public addNewUser(user: User) {
    return this.http.post<User>(environment.baseUrl+'signUp', user);
  }

  public addFriend(user: User, friendId: Number) {
    return this.http.put<User>(this.usersUrl+"/"+user.id+"/"+friendId, user);
  }

  public getFriends(userId: Number) {
    return this.http.get<User[]>(this.usersUrl+"/friends/"+userId);
  }

  public addProfileImage(image: File, userId: Number){
    const formData = new FormData(); 
        
    formData.append("file", image, image.name);
    return this.http.post<User>(this.usersUrl+"/image/"+userId, formData);
  }

  public authenticate(email: string, password: string) {
    return this.http
      .post<any>(environment.baseUrl+"authenticate", { email, password })
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
    //let currentUser:User = JSON.parse(sessionStorage.getItem("user")!) as User;
    //return currentUser;

    return this.getUserByEmail(sessionStorage.getItem("email") as string);

  }

  
}
