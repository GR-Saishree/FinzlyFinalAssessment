import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { CsvUploadComponent } from './csv-upload/csv-upload.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ManualentryComponent } from './manualentry/manualentry.component';
import { InvoiceComponent } from './invoice/invoice.component';
import { TransactionHistoryComponent } from './transaction-history/transaction-history.component';
import { PaymentUpdateComponent } from './payment-update/payment-update.component';
import { NavbarComponent } from './navbar/navbar.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component'




@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CsvUploadComponent,
    ManualentryComponent,
    InvoiceComponent,
    TransactionHistoryComponent,
    PaymentUpdateComponent,
    NavbarComponent,
    RegisterComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
   
    
   
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
