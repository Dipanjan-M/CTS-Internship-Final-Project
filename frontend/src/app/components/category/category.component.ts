import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';
import { Category } from 'src/app/models/category';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {
  dataSource:any;
  categoryId!:number;
  category:Category={
    id:0,
    categoryName:"Null",
    taxSlab:0
  };
  flag:boolean = false;
  constructor(private service:CategoryService) { }

  ngOnInit(): void {
    this.dataSource = this.getDataSource()
  }

  getDataSource(){
    this.service.getCategorys()
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

  submit(f:any){
    console.log(f.value)
    this.category = f.value
    this.service.postCategory(this.category)
      .pipe(first())
      .subscribe(
        res=>{
          if(res.status==200){
            alert(res.message)
          }
          console.log(res)
        },
        error=>{
          console.log(error)
        }
      )
      window.location.reload();
  }

  updateCategory(categoryId:number,categoryName:String,taxSlab:number){
    console.log(categoryId, categoryName, taxSlab)
    this.flag = true;
    this.category.id = categoryId;
    this.category.categoryName = categoryName;
    // this.category.hsnCode = hsnCode;
    this.category.taxSlab = taxSlab;
  }

  updateCategorySubmit(fModal:any, categoryId:number){
    console.log(this.category)
    // this.category.categoryName = fModal.value.categoryName1;
    // this.category.taxSlab = fModal.value.taxSlab1;
    // console.log(this.category)
    this.service.updateCategories(this.category,this.category.id).pipe(first())
      .subscribe(
        res=>{
          if(res.status==200){
            alert(res.message)
          }
          else{
            alert(res.message)
          }
        },
        error=>{
          alert(error.message)
        }
      )
      window.location.reload();
  }

  deleteCategory(categoryId:number){
    if(!confirm("Are you sure to delete the category")) return;
    this.service.deleteCategory(categoryId).pipe(first())
      .subscribe(
        res=>{
          if(res.status==200){
            alert(res.message)
          }
          else if(res.status==500){
            alert(res.message)
          }
        },
        error=>{
          alert(error.message)
        }
      )
    window.location.reload();
  }

}