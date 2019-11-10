import { Router } from '@angular/router';
import { HttpClient} from '@angular/common/http';
import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { Customer } from '../../model/customer.model';
import { WebapiService } from '../../service/webapi.service';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { customerResponse } from '../../model/customerResponse.model';

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})
export class BodyComponent implements OnInit {

  secretKey: string;  // local variable to store the uniqueId of customer
  customer: Customer; // local variable to store the updated customer values
  balance: number = 0; // local variable to emit the customer account balance
  error: boolean = false; // local variable to emit the error occurred in the selection
  showErrorModal = false; // local variable to control the Error-Modal
  showDefeatModal = false; // local variable to control the Defeat-Modal
  showSuccessModal = false; // local varible to control the Success-Modal
  winningAmount: number = 0;  // local variable to show the winning amount
  randomNumber: number = -1;  // local variable to show the present random number
  bettingOption: string = ""; // local variable to store the customer chosen option
  rouletteBoardForm: FormGroup; // local variable referencing to the Roulette Board
  showConfirmationModal = false;  // local variable to control the Confirmation Modal
  shouldDisableFormFields = false; // local variable to control the disabling/enabling 
                                   // Roulette Board input fields
  customerResponse: customerResponse; // local varible to store the response returned from
                                      // the WebApi Server

  // Emitting the balance changed event to Header Component
  @Output()
  balanceChanged = new EventEmitter<number>();

  // Emitting the occurence of error to the Footer Component
  @Output()
  errorChanged = new EventEmitter<boolean>();

  constructor(private httpClient:HttpClient,
    private webApiService:WebapiService,  // initializing the object of service class
    private router:Router) { }

  ngOnInit() {
    // retreiving the uniqueId entered by customer during login
    this.secretKey = localStorage.getItem('uniqueId');
    if(!this.secretKey)
      // if someone directly tried to access the Home Page without login
      this.router.navigate(['/login']);

    // retrieving the Customer Data
    this.webApiService.getCustomer(this.secretKey).subscribe((data) => {
      this.customer = data;
    });
    // Resetting the Roulette Form Values to 0
    this.resetFormValue();
  }

  // method to reset the Roulette Form
  resetFormValue() {
    const fb = new FormBuilder();
    this.rouletteBoardForm = fb.group({
      firstTwelve: new FormControl(0),
      secondTwelve: new FormControl(0),
      thirdTwelve: new FormControl(0),
      zero: new FormControl(0),
      onetoeighteen: new FormControl(0),
      nineteenthtothirtysix: new FormControl(0),
      even: new FormControl(0),
      odd: new FormControl(0)
    });
  }

  // method to Enable the input fields of Roulette Board
  enableControls() {
    this.error = false; // disabling the error element in footer component
    this.errorChanged.emit(this.error); // emmiting error to footer Component
    const allControls = Object.values(this.rouletteBoardForm.controls);
    allControls.forEach((c) => c.enable());
    this.resetFormValue();
  }

  // method triggered whenever customer changes the value of an input 
  // field of Roulette Board
  onControlChange(controlName: string) {
    this.bettingOption = controlName;
    const control = this.rouletteBoardForm.get(controlName);
    this.error = false; // disabling the error element in footer component
    this.errorChanged.emit(this.error); // emmiting error to footer Component
    // retrieving the value entered by customer on roulette board
    let blockedAmount = parseInt(control.value);

    if(blockedAmount <=0 || blockedAmount % 500 !== 0)
    {// if entered amount is either negative of not a multiple of 500
      // error occurred !!!
      this.error = true;
      this.errorChanged.emit(this.error);
      this.resetFormValue();
    }
    else
    if (blockedAmount > 0 && blockedAmount % 500 === 0) {
      // if entered amount is positive and mulitple of 500
      // disable all other input fields on Roulette Board
      const allControls = Object.values(this.rouletteBoardForm.controls);
      allControls.forEach((c) => c.disable());
    }
    control.enable();
  }

  // method triggered when customer click Play button on Roulette Board
  onRouletteFormSubmit() {
    this.showConfirmationModal = true;
  }

  // method to enable/disple the Play button on Roulette Board
  isFormDirty() {
    return this.rouletteBoardForm.dirty;
  }

  // method to close the Confirmation Modal
  closeConfirmationModal() {
    this.showConfirmationModal = false;
  }

  // method to close the Defeat Modal
  closeDefeatModal() {
    this.showDefeatModal = false;
  }

  // method to close the Error Modal
  closeErrorModal() {
    this.showErrorModal = false;
  }

  // method to close the Success Modal
  closeSuccessModal() {
    this.showSuccessModal = false;
  }

  // method triggered when user click the Sure Button on Confimation Modal
  onSure() {
    this.showConfirmationModal = false;
    // retrieving the latest Customer details from the server
    this.webApiService.getCustomer(this.secretKey).subscribe((data) => {
      this.customer = data;
    });

    // sending the customer update request to the server with chosen
    // Customer Details
    this.webApiService.updatecustomer(this.bettingOption,this.rouletteBoardForm.get(this.bettingOption).value)
    .subscribe((data) => {
      this.customerResponse = data;

      if(this.customerResponse.statusCode === -1) 
        // Customer blocked amount greater than accountValue
        this.showErrorModal = true;

      else if(this.customerResponse.statusCode === -2){
        // Customer lost the bet
        this.randomNumber = this.customerResponse.randomNumber;
        this.showDefeatModal = true;
      }
      else{
        // Betting amount entered by Customer is valid
        this.randomNumber = this.customerResponse.randomNumber;
        this.winningAmount = this.customerResponse.winningAmount;
        this.showSuccessModal = true;
      }
      
      // Retrieving the lateset customer from the server
      this.webApiService.getCustomer(this.secretKey).subscribe((data) => {
        this.customer = data;
        
      // emitting the balanced change event to the header.
      this.balanceChanged.emit(this.customer.accountBalance);
      });
    });
  }

  // method triggered when customer clicks Play Again button on Success Modal
  resetSuccessForm() {
    this.closeSuccessModal();
    this.rouletteBoardForm.reset();
    this.enableControls();
  }

  // method triggered when customer clicks Play Again button on Defeat Modal
  resetDefeatForm() {
    this.closeDefeatModal();
    this.rouletteBoardForm.reset();
    this.enableControls();
  }

  // method triggered when customer clicks Reset button on Error Modal
  resetErrorForm() {
    this.closeErrorModal();
    this.rouletteBoardForm.reset();
    this.enableControls();
  }
}
