import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ApiService {
  url: string = "http://localhost:8000/olx"
  httpOptions = {
    headers: new HttpHeaders({

    })
  };
  constructor(private http: HttpClient) { }

  public getListings(){
    return this.http.get(`${this.url}/`, this.httpOptions).pipe(
      catchError((error)=>{
        console.error('API Error:', error)
        throw new Error("Something went wrong. Please try again later.");
      })
    )
  }

  public postListing(body: any){
    return this.http.post(`${this.url}/`, body)
  }
}
