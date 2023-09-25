import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpService } from '../http.service';
import { HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit,OnDestroy {

  
  
  employeeId!:number;
  employeeOTP!:number;
  
  
  ngOnInit(): void {}

  ngOnDestroy(): void {}

  constructor(private httpService: HttpService,
    private router:Router,
    private authService:AuthService) {}

  login() {
    const headers=new HttpHeaders();
    this.httpService.loginEmployee(this.employeeId, this.employeeOTP).subscribe(
      (response:string) => {
        // Login successful
        console.log(response);
        alert(response);
        if(response.toLowerCase().includes('welcome')){
          console.log("Login successfull");
          this.authService.isLoggedIn=true;
          this.router.navigate(['']);
          
        }else{
          console.log("Login failed");
          
        }
        
        
      
      },
      (error) => {
        // Login failed
        console.log(error);
        
      }
    );
  }
  
}
