import { Component, Input } from '@angular/core';
import { Listing } from '../listing/listing';

@Component({
  selector: 'app-listing-card',
  standalone: true,
  imports: [],
  templateUrl: './listing-card.component.html',
  styleUrl: './listing-card.component.scss'
})
export class ListingCardComponent {
  @Input({required: true}) listing!: Listing;

  timeAgo(date: string): string {
    const now = new Date();
    const past = new Date(date);
    const seconds = Math.floor((now.getTime() - past.getTime()) / 1000);

    let interval = Math.floor(seconds / 31536000);
    if (interval >= 1) {
      return `${interval} year${interval !== 1 ? 's' : ''}.`;
    }
    interval = Math.floor(seconds / 2592000);
    if (interval >= 1) {
      return `${interval} month${interval !== 1 ? 's' : ''}.`;
    }
    interval = Math.floor(seconds / 86400);
    if (interval >= 1) {
      return `${interval} day${interval !== 1 ? 's' : ''}.`;
    }
    interval = Math.floor(seconds / 3600);
    if (interval >= 1) {
      return `${interval} hour${interval !== 1 ? 's' : ''}.`;
    }
    interval = Math.floor(seconds / 60);
    if (interval >= 1) {
      return `${interval} minute${interval !== 1 ? 's' : ''}.`;
    }
    return `${Math.floor(seconds)} second${seconds !== 1 ? 's' : ''}.`;
  }
}
