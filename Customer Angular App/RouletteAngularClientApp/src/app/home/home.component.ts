import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  balance: number; // balanced variable reflecting the customer-balance
                   // from Body Component
  error: boolean; // error variable reflecting error occured event from
                  // Body Component

  constructor() { }

  ngOnInit() {
  }

  // method to bind the balance variable in Header Component
  onBalanceChanged(balance: number){
    this.balance = balance;
  }

  // method to bind the error event in Footer Component
  onErrorChanged(error:boolean){
    this.error = error;
  }
}
