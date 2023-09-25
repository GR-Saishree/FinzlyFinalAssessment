import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { CsvUploadComponent } from './csv-upload/csv-upload.component';
import { PaymentUpdateComponent } from './payment-update/payment-update.component';
import { TransactionHistoryComponent } from './transaction-history/transaction-history.component';
import { InvoiceComponent } from './invoice/invoice.component';
import { ManualentryComponent } from './manualentry/manualentry.component';

const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'register',component:RegisterComponent},
  {path:'login',component:LoginComponent},
  {path:'csv-upload',component:CsvUploadComponent},
  {path:'payment-update',component:PaymentUpdateComponent},
  {path:'transaction-history',component:TransactionHistoryComponent},
  {path:'invoice',component:InvoiceComponent},
  {path:'manualentry',component:ManualentryComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
