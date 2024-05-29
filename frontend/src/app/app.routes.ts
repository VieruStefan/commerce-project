import { Routes } from '@angular/router';
import { HomeComponent } from '../home/home.component';
import { CreateListingComponent } from '../create-listing/create-listing.component';
import { IndexComponent } from '../index/index.component';

export const routes: Routes = [
    { path: "", component: IndexComponent},
    { path: "home", component: HomeComponent},
    { path: "add", component: CreateListingComponent}
];
