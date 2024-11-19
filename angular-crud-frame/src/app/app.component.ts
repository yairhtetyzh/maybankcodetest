import { Component } from '@angular/core';
import { AuthService } from './services/auth.service'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  constructor(public auth: AuthService) {}
  title = 'angular-crud-frame';

  sidebarOpen = true;

  ngOnInit(): void {
    console.log("Is Authenticated");
    console.log(this.auth.isAuthenticatedUser());
  }

  toggleSidebar(){
    this.sidebarOpen = this.sidebarOpen? false : true;
  }
}
