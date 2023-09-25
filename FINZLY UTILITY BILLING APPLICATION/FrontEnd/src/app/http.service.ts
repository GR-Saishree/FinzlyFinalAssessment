import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Invoice } from './invoice/invoice.model';
import { PaymentTransaction } from './transaction-history/transaction-history.model';

@Injectable({
  providedIn: 'root',
})
export class HttpService {
  private apiUrl = 'http://localhost:8080/'

  constructor(private http: HttpClient) {}

  uploadCSVFile(file: File): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('file', file, file.name);

    const headers = new HttpHeaders();
    headers.set('Content-Type','multipart/form-data');
    
    return this.http
      .post(`${this.apiUrl}/bulkUpload`, formData, { headers })
      .pipe(
        catchError((error) => {
          return throwError('File upload failed: ' + error);
        })
      );
  }

  loginEmployee(employeeId:number,employeeOTP:number){
    const headers = new HttpHeaders();
    return this.http.get(`http://localhost:8080/loginEmployee?employeeID=${employeeId}&employeeOTP=${employeeOTP}`,{headers,responseType:'text'});
  }

  manualCustomerEntry(customerDetails:any):Observable<string>{
    const headers = new HttpHeaders();
    const options={
      headers,
      responseType:'text' as 'json',
    }
    return this.http.post<string>(`${this.apiUrl}manualCustomerEntry`,customerDetails,options);
  }

  manualBillingEntry(billingDetails:any):Observable<string>{
    const headers = new HttpHeaders();
    const options={
      headers,
      responseType:'text' as 'json',
    }
    return this.http.post<string>(`${this.apiUrl}manualBillEntry`,billingDetails,options);
  }

  getInvoiceData(billingID:number):Observable<Invoice[]>{
    return this.http.get<Invoice[]>(`${this.apiUrl}getGeneratedInvoice/${billingID}`);

  }

  getTransactionHistory(cid:number):Observable<PaymentTransaction[]>{
    return this.http.get<PaymentTransaction[]>(`${this.apiUrl}getTransactionHistory/${cid}`);

  }

  markPaymentMadeInCash(transactionid:number,status:string):Observable<any>{
    const url=`${this.apiUrl}markPaymentMadeInCash/${transactionid}`;
    const params=new HttpParams().set('status',status);

    const headers = new HttpHeaders().set('Content-Type','text/plain')
    console.log(url);
    
    
    
    return this.http.put(url,null,{params: params, headers:headers,responseType:'text'});
  }

  signupEmployee(employee:any):Observable<any>{

    const headers = new HttpHeaders({'Content-Type':'application/json'});
    return this.http.post(`${this.apiUrl}signupEmployee`,JSON.stringify(employee),{headers})
  }
  
}
