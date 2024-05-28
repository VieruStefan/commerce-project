import { Component } from '@angular/core';
import { ApiService } from '../api.service';
import { ListingCardComponent } from '../listing-card/listing-card.component';
import { Listing } from '../listing/listing';
import { CommonModule } from '@angular/common';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { FormsModule, NgModel } from '@angular/forms';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [ListingCardComponent, CommonModule,
     RouterLinkActive, RouterLink,
    FormsModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  listings: Array<Listing> = [];
  constructor(private apiService: ApiService, private http: HttpClient, private router: Router){  }
  ngOnInit(){
    this.apiService.getListings().subscribe(
      (res) => {
        if(Array.isArray(res)){
          this.listings = res.map((body) =>{
            const fields = body
            return {
              id: fields.id,
              title: fields.title,
              description: fields.description,
              seller: fields.seller,
              image_url: fields.image_url,
              updated_date: fields.updated_date,
              pub_date: fields.pub_date
            } as Listing;
          })
        }
        console.log(res)
      }
    )
  }
  loginData = { username: '', password: '' };

  onSubmit() {
    this.http.post(`${this.apiService.url}/accounts/login/`, this.loginData).subscribe(
      (response) => {
        console.log('Login successful:', response);
        this.router.navigate(['/']);
      },
      (error) => {
        console.error('Login failed:', error);
        alert('Invalid username or password');
      }
    );
  }
}