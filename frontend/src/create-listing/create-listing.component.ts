import { Component } from '@angular/core';
import {MatIconModule} from '@angular/material/icon';

import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ApiService } from '../api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-listing',
  standalone: true,
  imports: [ReactiveFormsModule, MatIconModule],
  templateUrl: './create-listing.component.html',
  styleUrl: './create-listing.component.scss'
})
export class CreateListingComponent {
  formListing!: FormGroup;
  file!: File;
  fileName!: string;

  constructor(private formBuilder: FormBuilder,
     private api: ApiService,
      private router: Router){}

  ngOnInit(){
    this.formListing = this.formBuilder.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      picture: [null]
    })
  }

  onFileSelected(event: any) {
    this.file = event.target.files[0];

    if (this.file instanceof File) {
      this.fileName = this.file.name;
      console.log(this.file);
      // this.formListing.patchValue({ picture: this.file });
    }
  }
  
  submit(){
    const formDataListing = new FormData();
    formDataListing.append('title', this.formListing.get('title')?.value);
    formDataListing.append('file', this.file);
    formDataListing.append('seller', "Stefan Vieru");
    formDataListing.append('description', this.formListing.get('description')?.value);

    const listing = {
      title: this.formListing.get('title')?.value,
      description: this.formListing.get('description')?.value,
      seller: "Stefan Vieru",
      image_url: this.fileName
    }
    const body = new Blob([JSON.stringify(listing)], { type: "application/json" });
    formDataListing.append('listing', body);
    if (this.file) {
      formDataListing.append('picture', this.file);
    }
    this.api.postListing(formDataListing).subscribe({
        complete: () => {
          console.info('complete');
          this.router.navigate(['/']);
        },
        next: (res) => console.log(res),
        error: (e) => console.error('Error:', e)   
      }
    );
  }
}
