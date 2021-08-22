import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import Swal from 'sweetalert2';

import { AuthenticationService } from "src/app/services/authentication.service";

@Component({
  selector: 'app-admin-register',
  templateUrl: './admin-register.component.html',
  styleUrls: ['./admin-register.component.css']
})
export class AdminRegisterComponent implements OnInit {
  adminRegisterForm!: FormGroup;
  loading = false;
  submitted = false;
  returnUrl?: string;
  error = '';
  registerData! :{
    firstName:'',
    lastName:'',
    emailId:'',
    password:'',
    admin:true,
    activeUser: true

  }


  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService
  ) { 
    
  }

  ngOnInit(): void {
    this.adminRegisterForm = this.formBuilder.group({
      firstName: ['', [Validators.required,Validators.minLength(3), Validators.maxLength(30)]],
      lastName: ['', [Validators.required,Validators.minLength(3), Validators.maxLength(30)]],
      emailId: ['', [Validators.required,Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
      password: ['', Validators.required]
    });

    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  // convenience getter for easy access to form fields
  get f() { return this.adminRegisterForm.controls; }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.adminRegisterForm.invalid) {
      return;
    }
    
    this.registerData = {
      firstName : this.f.firstName.value,
      lastName : this.f.lastName.value,
      emailId : this.f.emailId.value,
      password: this.f.password.value,
      admin : true,
      activeUser : true
    }

    this.loading = true;



    Swal.fire({
      title: 'Are you sure?',
      text: '',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, create it!',
      cancelButtonText: 'No, leave it'
    }).then((result) => {
      if (result.isConfirmed) {
        this.authenticationService.adminRegister(this.registerData)
        .pipe(first())
        .subscribe(
          data => {
            console.log(data)
            if(data.status == 200 || data.status == 201) {
              Swal.fire(
                'Success!',
                `Admin account with USERNAME - ${data.data.userName} has been created successfully`,
                'success'
                )
                
            }
            else {
              Swal.fire(
                'Failure!',
                `${data.message}`,
                'error'
                )
              this.error = data.message;
              this.loading = false;
            } 
          },
          error => {
            this.error = error;
            this.loading = false;
            Swal.fire(
              'Cancelled',
              `${error}`,
              'error'
            )
          }
        );
      }
    })
  }
 
}
