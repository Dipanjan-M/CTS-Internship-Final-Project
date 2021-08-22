import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  
  name?: string;
  userType?: string;
  categoryMap: any;
  productMap: any;

  constructor(
    private serviceProduct:ProductService,
    private authService: AuthenticationService
    ) { 
      this.name = this.authService.currentUserValue.firstName + " " + this.authService.currentUserValue.lastName;
      this.userType = this.authService.currentUserValue.role;
    }

  ngOnInit(): void {
    this.getCategoryName();
  }

  getCategoryName(){
    this.serviceProduct.getCategoryNames().pipe(first())
      .subscribe(
        res=>{
          if(res.status==200)
            this.categoryMap = res.data;
          console.log(res)
        },
        error=>{
          console.log(error);
        }
      )
  }

 
}
