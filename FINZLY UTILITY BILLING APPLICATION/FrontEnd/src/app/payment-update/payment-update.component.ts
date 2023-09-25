import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';


@Component({
  selector: 'app-payment-update',
  templateUrl: './payment-update.component.html',
  styleUrls: ['./payment-update.component.css']
})
export class PaymentUpdateComponent implements OnInit {

  transactionid!:number;
  status!:string;
  responseMessage!:string;

  constructor(private http:HttpService) { }

  ngOnInit(): void {
  }

  markPayment(){
    this.http.markPaymentMadeInCash(this.transactionid,this.status).subscribe(
      (response:string)=>{
        console.log(response);
        alert(response);
        this.responseMessage=response;
      },
      (error:any)=>{
        console.error('Error: ',error);
        alert(error);
        this.responseMessage='Error occurred while marking payment';
      }

    )
    
}
}
