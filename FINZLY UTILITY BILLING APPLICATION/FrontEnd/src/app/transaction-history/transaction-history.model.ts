export interface PaymentTransaction{
    transactionid:number;
    bid:number;
    cid:number;
    paymentamt:number;
    paymentmethod:string;
    referencenumber:number;
    transactiondate:Date;
    transactionstatus:string;
    cardnumber:number;
}