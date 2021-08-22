import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  productId!:number;
  categoryMap:any[] = [{

  }];
  constructor(private serviceProduct: ProductService) { }

  ngOnInit(): void {
    this.getDataSource()
    this.getCategoryName()
  }

  productHeaders = ["Product Id","Product Name","Category Name","Tax Slab","Quantity","MRP","Manufacturing Date","Expiry Date","Action"]
  dataSource:any;
  // categoryId!:number;
  newCategoryId:any;
  updateCategoryId:any;
  product:Product={
    id:0, 
    exp: null, 
    mfd: null, 
    mrp:0, 
    productName:"",
    quantity:0
  };
  flag:boolean = false;

  getDataSource(){
    this.serviceProduct.getProduct()
    .pipe(first()).subscribe(res=>{
      if(res.status==200){
        console.log(res);
        this.dataSource = res.data;
      }
      else{
        alert(res.message);
      }
    });
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

  inflateInputFields(productId:any, productName:any, mrp:any, quantity:any, mfd:any, exp:any, categoryId:any){
    this.flag = true;
    this.product.id = productId;
    this.product.productName = productName;
    this.product.mrp = mrp;
    this.product.quantity = quantity;
    this.product.mfd = mfd;
    this.product.exp = exp;
    this.updateCategoryId = categoryId;
    document.documentElement.scrollTop = 0;
  }
  submit(f:any){
    console.log(f.value)
    console.log(this.newCategoryId)
    // console.log(this.product.categoryId)
    this.product = f.value;
    // this.product.categoryId = f.value.categoryId;
    console.log(this.product)
    this.serviceProduct.postProduct(this.product,this.newCategoryId)
      .pipe(first())
      .subscribe(
        res=>{
          if(res.status==200){
            alert(res.message)
          }
          else{
            alert(res.message)
          }
          // console.log(res)
          this.getDataSource();
          f.reset();
          document.documentElement.scrollTop = document.documentElement.scrollHeight;
        },
        error=>{
          console.log(error)
        }
      )
  }

  updateProduct(fModal:any){
    // console.log(fModal.value)
    this.product.exp = fModal.value.expiryDate1;
    this.product.mfd = fModal.value.mfd1;

    // console.log(this.product)
    this.serviceProduct.updateProduct(this.product,this.updateCategoryId).pipe(first())
      .subscribe(
        res=>{
          if(res.status==200){
            alert(res.message)
          }
          else{
            alert(res.message)
          }
          this.getDataSource();
          fModal.reset();
          document.documentElement.scrollTop = document.documentElement.scrollHeight;
          this.flag = !this.flag;
        },
        error=>{
          alert(error.message)
        }
      )
      // window.location.reload();
  }

  deleteProduct(productId:number){
    if(!confirm("Are you sure to delete the product")) return;
    this.serviceProduct.deleteProduct(productId).pipe(first())
      .subscribe(
        res=>{
          if(res.status==200){
            alert(res.message)
          }
          else if(res.status==500){
            alert(res.message)
          }
          this.getDataSource();
        },
        error=>{
          alert(error.message)
        }
      )
    // window.location.reload();
  }

}
