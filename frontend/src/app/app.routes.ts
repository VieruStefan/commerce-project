import { Routes } from '@angular/router';
import { HomeComponent } from '../home/home.component';
import { CreateListingComponent } from '../create-listing/create-listing.component';

export const routes: Routes = [
    { path: "", component: HomeComponent},
    { path: "add", component: CreateListingComponent}
];
