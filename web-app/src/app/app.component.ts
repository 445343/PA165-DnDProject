import {Component, OnInit} from '@angular/core';
import {UserService} from "./services/user/user.service";
import {TestService} from "./test.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'web-app';
  constructor(
    private testService: TestService
  ) { }

  ngOnInit(): void {
    this.testService.insertTestData()
      .subscribe(data => {
        console.log(data);
      });
  }
}
