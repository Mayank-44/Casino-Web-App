import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from 'src/app/model/customer.model';
import { WebapiService } from '../../service/webapi.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  customer:Customer;
  uniqueId: string;
  @Input()
  initialBalance: number;

  constructor(private router:Router,
              private webApiService: WebapiService) { }

  ngOnInit() {
    this.uniqueId = localStorage.getItem('uniqueId');
    this.webApiService.getCustomer(this.uniqueId).subscribe((data) => {
      this.customer = data;
      this.initialBalance = this.customer.accountBalance;
    });
  }

  logout(){
    localStorage.removeItem("uniqueId");
    this.router.navigate(["/login"]);
  }
}
