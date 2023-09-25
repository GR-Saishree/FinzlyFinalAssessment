import { Component, OnInit } from '@angular/core';
import { PaymentTransaction } from './transaction-history.model';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-transaction-history',
  templateUrl: './transaction-history.component.html',
  styleUrls: ['./transaction-history.component.css']
})
export class TransactionHistoryComponent implements OnInit {

  cid!:number;
  transactionHistory: PaymentTransaction[]=[];
  errorMessage:string|null=null;

  constructor(private http:HttpService) { }

  ngOnInit(): void {
  }

  fetchTransactionHistory(){
    this.errorMessage=null;
    if(this.cid){
      this.http.getTransactionHistory(this.cid).subscribe((data)=>{
        console.log(data);
        if(data.length==0){
          alert('Transaction History not found for the specified customer');
          this.errorMessage='Transaction History not found for the specified customer';
        }else{
          this.transactionHistory=data;
        }
        
        
      });

    }
    else{
      this.errorMessage='Please enter a valid billing ID'

    }
  }
      
}

  


