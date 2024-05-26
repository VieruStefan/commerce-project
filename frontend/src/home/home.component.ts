import { Component } from '@angular/core';
import { ApiService } from '../api.service';
import { ListingCardComponent } from '../listing-card/listing-card.component';
import { Listing } from '../listing/listing';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [ListingCardComponent, CommonModule, RouterLinkActive, RouterLink],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  listings: Array<Listing> = [];
  constructor(private apiService: ApiService){  }
  ngOnInit(){
    this.apiService.getListings().subscribe(
      (res) => {
        if(Array.isArray(res)){
          this.listings = res.map((item) =>{
            const fields = item.fields
            return {
              id: item.pk,
              title: fields.title,
              description: fields.description,
              seller: fields.seller,
              image_url: fields.image_url,
              pub_date: fields.pub_date
            } as Listing;
          })
        }
        console.log(res)
      }
    )
  }
}