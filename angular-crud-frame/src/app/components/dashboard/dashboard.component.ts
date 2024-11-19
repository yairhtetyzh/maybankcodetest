import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {

  creditCardMaximumAmount: number = 0;
  creditCardMaximumInterest: number = 0;
}
