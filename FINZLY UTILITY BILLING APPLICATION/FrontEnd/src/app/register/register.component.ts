import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  employee:any={};
  enteredUsername:string='';
  constructor(private http:HttpService) { }

  ngOnInit(): void {
  }

  onSubmit(){
    if(this.enteredUsername==='admin'){
      this.http.signupEmployee(this.employee).subscribe(
        (response)=>{
          alert(`Employee Registration Success!\nEmployee ID: ${response.employeeID}\nEmployee OTP: ${response.employeeOTP}`)
          console.log("Employee Registration success");
          
        },
        (error)=>{
          console.error("Employee registration Failed");
          alert("Employee Registration Failed");
          
        }
      );
    }else{
      console.error('Not authorized to register employee');
      
    }
  }

}
