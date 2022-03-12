import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class DataService {


  subjectNotifier: Subject<void> = new Subject<void>();

  foundUsersNotifier: Subject<any> = new Subject<any>();
 
  constructor() { }
 
  notifyAboutChange() {
    this.subjectNotifier.next();
  }

  notifyAboutFoundUsers(foundUsers: User[]) {
    this.foundUsersNotifier.next(foundUsers);
  }
}
