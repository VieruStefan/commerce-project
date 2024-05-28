import { ChangeDetectorRef, Component, IterableDiffer, IterableDiffers } from '@angular/core';
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
  private differ: IterableDiffer<Listing>;
  constructor(private apiService: ApiService,
      private http: HttpClient,
      private router: Router,
      private differs: IterableDiffers,
       private cdr: ChangeDetectorRef){
    this.differ = this.differs.find(this.listings).create();
    }
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
  ngDoCheck(): void {
    const changes = this.differ.diff(this.listings);
    if (changes) {
      changes.forEachAddedItem(record => {
        console.log('New listing added:', record.item);
        this.updatePage();
      });
    }
  }

  updatePage(): void {
    this.cdr.detectChanges();

  }
}