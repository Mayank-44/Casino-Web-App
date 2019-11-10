import { Component, OnInit } from '@angular/core';
import { Customer } from '../model/customer.model';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { WebapiService } from '../service/webapi.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  customer:Customer; // local variable to store the customer details
  uniqueId : string = ""; // local variable to store the customer unique Id
  customerExists:boolean = true;  // flag to check if customer is logged in or not

  constructor(private http:HttpClient,private router:Router,
              private webApiService:WebapiService) { }

  ngOnInit() {
    // verifying if customer is logged in then redirect to home page
    if(localStorage.getItem('uniqueId'))
      this.router.navigate(['/home']);
  }
  
  // method triggered when customer clicks the Sign In button on the login form
  submit(){
   this.customerExists = false;
    this.webApiService.getCustomer(this.uniqueId)
    .subscribe((response) => {
      this.customer = response;
      if(this.customer.customerName)
      {
        // storing the unique id of customer in the local storage
        localStorage.setItem('uniqueId',this.uniqueId);
        this.router.navigate(['/home']);
      }
    })
  }
}
