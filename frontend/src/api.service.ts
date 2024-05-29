import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ApiService {
  url: string = "http://oferte-directe.api:7200/listings";
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

  url_users: string = "http://54.243.193.76:7200/users";

  public getUsers(){
    return this.http.get(`${this.url_users}/`, this.httpOptions).pipe(
      catchError((error)=>{
        console.error('API Error:', error)
        throw new Error("Something went wrong. Please try again later.");
      })
    )
  }

  public getUserByDetails(firstName: string, lastName: string, email: string){
    const params = { firstName, lastName, email };
    return this.http.get<any>(`${this.url_users}/`, { ...this.httpOptions, params }).pipe(
      catchError((error) => {
        console.error('API Error:', error);
        throw new Error("Something went wrong. Please try again later.");
      })
    );
  }

  public postUser(body: any){
    return this.http.post<any>(`${this.url_users}/`, body, this.httpOptions).pipe(
      catchError((error) => {
        console.error('API Error:', error);
        throw new Error("Something went wrong. Please try again later.");
      })
    );
  }
}
