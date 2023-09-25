export interface Invoice{
    
    invoiceID:number;
    billingID:number;
    customerID:number;
    customerName:string;
    invoiceDate:Date;
    billDueDate:Date;
    totalAmount:number;
    earlyPaymentDiscount:number;
    onlinePaymentDiscount:number;
    totalDiscountAmount:number;
}