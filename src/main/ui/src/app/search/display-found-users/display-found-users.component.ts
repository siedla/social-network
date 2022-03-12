import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { User } from 'src/app/model/user';
import { DataService } from 'src/app/services/data.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-display-found-users',
  templateUrl: './display-found-users.component.html',
  styleUrls: ['./display-found-users.component.css']
})
export class DisplayFoundUsersComponent implements OnInit {

  constructor(private userService: UserService, private dataService: DataService) {
  }

  notifierSubscription: Subscription = this.dataService.foundUsersNotifier.subscribe(data => {
    this.foundUsers = data;
    console.log(data);
});

  foundUsers: User[] = [];

  ngOnInit() {

  }


}
