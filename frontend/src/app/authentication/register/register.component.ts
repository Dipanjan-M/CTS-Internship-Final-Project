import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { AuthenticationService } from "src/app/services/authentication.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm!: FormGroup;
  loading = false;
  submitted = false;
  returnUrl?: string;
  error = '';
  registerData! :{
    firstName:'',
    lastName:'',
    emailId:'',
    password:'',
    admin:false,
    activeUser: false

  }


  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService
  ) { 
    // redirect to home if already logged in
    if (this.authenticationService.currentUserValue) { 
      this.router.navigate(['/']);
    }
  }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      firstName: ['', [Validators.required,Validators.minLength(5), Validators.maxLength(30)]],
      lastName: ['', [Validators.required,Validators.minLength(5), Validators.maxLength(30)]],
      emailId: ['', [Validators.required,Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
      password: ['', Validators.required]
    });

    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  
  // convenience getter for easy access to form fields
  get f() { return this.registerForm.controls; }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }
    
    this.registerData = {
      firstName : this.f.firstName.value,
      lastName : this.f.lastName.value,
      emailId : this.f.emailId.value,
      password: this.f.password.value,
      admin : false,
      activeUser : false
    }

    this.loading = true;
    this.authenticationService.register(this.registerData)
    .pipe(first())
    .subscribe(
      data => {
        console.log(data)
        if(data.status == 500) {
          this.error = data.message;
          this.loading = false;
        } else {

          this.router.navigate(['/login']);
        }
      },
      error => {
        this.error = error;
        this.loading = false;
      }
    );
  }
}