import { Component, OnInit } from '@angular/core';
import { DataService } from '../services/data.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  constructor(private userService: UserService, private dataService: DataService) { }

  name!: string;

  ngOnInit(): void {
  }


  search() {
    var fullName = this.name.split(' ');
    var firstName = fullName[0];
    var lastName = fullName[fullName.length - 1];

    this.userService.findUserByName(firstName, lastName).subscribe(data => {
      this.dataService.notifyAboutFoundUsers(data);
    });
  }

}
