import { ChangeDetectorRef, Component, IterableDiffer, IterableDiffers } from '@angular/core';
import { ApiService } from '../api.service';
import { ListingCardComponent } from '../listing-card/listing-card.component';
import { Listing } from '../listing/listing';
import { CommonModule } from '@angular/common';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { FormsModule, NgModel } from '@angular/forms';
import { Subscription } from 'rxjs/internal/Subscription';
import { interval } from 'rxjs/internal/observable/interval';
import { switchMap } from 'rxjs/internal/operators/switchMap';

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
      private differs: IterableDiffers,
       private cdr: ChangeDetectorRef){
    this.differ = this.differs.find(this.listings).create();
    }
    private refreshSubscription: Subscription | undefined;
    ngOnInit(): void {
      // Initial data retrieval
      this.getData();
  
      // Fetch data every 10 seconds
      this.refreshSubscription = interval(100000).pipe(
        switchMap(() => this.apiService.getListings())
      ).subscribe(
        (res) => {
          if (Array.isArray(res)) {
            this.listings = res.map((body) => ({
              id: body.id,
              title: body.title,
              description: body.description,
              price: body.price,
              user: body.user,
              image_url: body.image_url,
              updated_date: body.updated_date,
              pub_date: body.pub_date
            }));
          }
          console.log(res);
        },
        (error) => {
          console.error('Error fetching listings:', error);
        }
      );
    }
  
    ngOnDestroy(): void {
      if (this.refreshSubscription) {
        this.refreshSubscription.unsubscribe();
      }
    }
  
    private getData(): void {
      this.apiService.getListings().subscribe(
        (res) => {
          if (Array.isArray(res)) {
            this.listings = res.map((body) => ({
              id: body.id,
              title: body.title,
              description: body.description,
              price: body.price,
              user: body.user,
              image_url: body.image_url,
              updated_date: body.updated_date,
              pub_date: body.pub_date
            }));
          }
          console.log(res);
        },
        (error) => {
          console.error('Error fetching listings:', error);
        }
      );
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