import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Customer } from '../model/customer.model';
import { customerResponse } from '../model/customerResponse.model';

@Injectable({
  providedIn: 'root'
})
export class WebapiService {
  // URL of the java web api service
  private readonly url = 'http://localhost:8080/rouletteapplication2/webapi/customer/';
  
  constructor(private httpClient:HttpClient) { }

  // method to retrieve the customer details
  getCustomer(uniqueId:String){
    return this.httpClient.get<Customer>(this.url+uniqueId);
  }

  // method to update the customer details
  updatecustomer(bettingOption : string, bettingValue:number){
    let id = localStorage.getItem('uniqueId');
    return this.httpClient.put<customerResponse>(this.url + id + "/" + bettingOption + "/" + bettingValue,null);
  }
}
