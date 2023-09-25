import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-manualentry',
  templateUrl: './manualentry.component.html',
  styleUrls: ['./manualentry.component.css']
})
export class ManualentryComponent implements OnInit {

 customerDetails:any={
  customername:'',
  cid:'',
  emailid:'',
  mobileno:'',
  houseno:'',
  address:'',
  pincode:'',
  state:'',
  country:''

 };

 billingDetails:any={
  customerID:'',
  unitConsumed:'',
  startDate:'',
  endDate:'',
  billDueDate:''
 }

 isCustomerIdReadOnly:boolean=false;
  constructor(private http:HttpService) { }

  ngOnInit(): void {
  }

  onSubmit(){
    const apiUrl = 'http://localhost:8080/';
    const headers=new HttpHeaders();
    console.log(this.customerDetails);
    
    this.http.manualCustomerEntry(this.customerDetails).subscribe(
      (response:string) =>{
        console.log("Success",response);
        alert(response);
        
      },
      (error)=>{
        console.error('Error',error);
        
      }
    )
    this.isCustomerIdReadOnly=true;
    this.billingDetails.customerID=this.customerDetails.cid;
  }

  onBillDetailSubmit(){
    const apiUrl = 'http://localhost:8080/';
    const headers=new HttpHeaders();
    console.log(this.billingDetails);
    
    this.http.manualBillingEntry(this.billingDetails).subscribe(
      (response:string) =>{
        console.log("Success",response);
        alert(response);
        
      },
      (error)=>{
        console.error('Error',error);

        
      }
    )
  }

}
