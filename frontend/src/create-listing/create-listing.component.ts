import { Component } from '@angular/core';
import {MatIconModule} from '@angular/material/icon';

import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ApiService } from '../api.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

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

  
  Toast = Swal.mixin({
    toast: true,
    position: 'center',
    iconColor: 'white',
    customClass: {
      popup: 'colored-toast',
    },
    showConfirmButton: false,
    timer: 1500,
    timerProgressBar: true,
  })

  constructor(private formBuilder: FormBuilder,
     private api: ApiService,
      private router: Router){}

  ngOnInit(){
    this.formListing = this.formBuilder.group({
      title: ['', Validators.required],
      price: [0, Validators.required],
      description: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', Validators.required],
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
    // formDataListing.append('title', this.formListing.get('title')?.value);
    // formDataListing.append('description', this.formListing.get('description')?.value);
    // formDataListing.append('price', this.formListing.get('price')?.value);
    // formDataListing.append('user_id', "1");

    const firstName = this.formListing.get('firstName')?.value;
    const lastName = this.formListing.get('lastName')?.value;
    const email = this.formListing.get('email')?.value;

    this.api.getUserByDetails(firstName, lastName, email).subscribe({
        complete: () => console.info('completed fetching user'),
        next: (res) => {
          console.log('fetched user:', res);
          this.submit_form(res)
        },
        error: (e) => {
          console.error('Error fetching the user, creating a new one..', e)                
          Swal.fire({
            icon: 'warning',
            title: 'Cont inexistent',
            text: 'Contul dumneavoastra a fost creat.'
          }).then(() => {
          });
          this.api.postUser(
            {
              firstName: firstName,
              lastName: lastName,
              email: email
            }
          ).subscribe({
            complete: () => console.info('complete'),
            next: (res) => console.log(res),
            error: (e) => console.error('Error:', e)   
          })
        }
      });

  }
  submit_form(res:any){
    const formDataListing = new FormData();

    const listing = {
      title: this.formListing.get('title')?.value,
      description: this.formListing.get('description')?.value,
      price: parseInt(this.formListing.get('price')?.value),
      user: res
    }
    const body = new Blob([JSON.stringify(listing)], { type: "application/json" });
    formDataListing.append('listing', body);
    if (this.file) {
      formDataListing.append('picture', this.file);
    }
    this.api.postListing(formDataListing).subscribe({
        complete: () => {
          console.info('complete');
        },
        next: (res) =>{ 
          console.log(res)
          Swal.fire({
            icon: 'success',
            title: 'Anunț creat cu succes!'
          }).then(() => {
            window.location.href = "/home";
          });
      },
        error: (e) => console.error('Error:', e)   
      }
    );
  }
}
