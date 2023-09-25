import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-csv-upload',
  templateUrl: './csv-upload.component.html',
  styleUrls: ['./csv-upload.component.css']
})
export class CsvUploadComponent implements OnInit {

  csvFile: File | null = null;
  
  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }


  onFileSelected(event: any) {
    this.csvFile = event.target.files[0];
  }

  uploadCSV() {
    if (this.csvFile) {
      const formData: FormData = new FormData();
      formData.append('file', this.csvFile, this.csvFile.name);
      
      
      
      const apiUrl = 'http://localhost:8080/bulkUpload';
      const headers=new HttpHeaders();
      headers.set('Content-Type','mutipart/form-data');
  

      this.http.post(apiUrl, formData,{headers,responseType:'text'}).subscribe(
        (response:string) => {
          // Handle success response from the API
          console.log('Upload successful:', response);
          alert(response);
          // Display the response in a pop up box (alert)
         
        },
        (error) => {
          // Handle error response from the API
          console.error('Upload error:', error);
          alert(error);
          
        }
      );
    }

  }
}
