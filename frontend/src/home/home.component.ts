import { ChangeDetectorRef, Component, IterableDiffer, IterableDiffers } from '@angular/core';
import { ApiService } from '../api.service';
import { ListingCardComponent } from '../listing-card/listing-card.component';
import { Listing } from '../listing/listing';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [ListingCardComponent, CommonModule,
     RouterLinkActive, RouterLink,
    FormsModule, ReactiveFormsModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  listings: Array<Listing> = [];
  filteredListings: Array<Listing> = [];
  searchQuery = '';
  private differ: IterableDiffer<Listing>;

  constructor(private apiService: ApiService,
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
              price: fields.price,
              user: fields.user,
              image_url: fields.image_url,
              updated_date: fields.updated_date,
              pub_date: fields.pub_date
            } as Listing;
          })
          this.filteredListings = this.listings;
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

  filterListings(): void {
    this.filteredListings = this.listings.filter(listing =>
      listing.title.toLowerCase().includes(this.searchQuery.toLowerCase())
    );
  }

  onSearchChange(): void {
    this.filterListings();
  }

  updatePage(): void {
    this.cdr.detectChanges();

  }
}