import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { Invoice } from './invoice.model';

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.css']
})
export class InvoiceComponent implements OnInit {

  invoices: Invoice[]=[];
  billingID!:number;
  errorMessage:string|null=null;

  constructor(private http:HttpService) { }

  ngOnInit(): void {
  }

  loadInvoiceData(){
    this.errorMessage=null;

    if(this.billingID){
      this.http.getInvoiceData(this.billingID).subscribe((data)=>{
        console.log(data);
        if(data.length==0){
          alert('Invoice not found for the specified billing');
          this.errorMessage='Invoice not found for the specified billing';
        }else{
          this.invoices=data;
        }
        
        
      });

    }
    else{
      this.errorMessage='Please enter a valid billing ID'

    }
  }

}
