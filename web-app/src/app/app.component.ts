import {Component, OnInit} from '@angular/core';
import {UserService} from "./services/user/user.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'web-app';
  constructor(
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.userService.insertTestData()
      .subscribe(data => {
        console.log(data);
      });
  }
}
