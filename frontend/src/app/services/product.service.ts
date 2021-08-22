import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponse } from 'src/app/models/response';
import { Product } from 'src/app/models/product';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

 
  endpoint = environment.apiUrl;
  // httpHeader = {
  //   headers: new HttpHeaders({
  //     'Content-Type': 'application/json'
  //   })
  // } 
  constructor(private http: HttpClient) { }

  getProduct(): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.endpoint+'/product/get-all-products');
  }

  

  getCategoryNames(){
    return this.http.get<ApiResponse>(this.endpoint+"/category/get-all-categories");
  }

  

  postProduct(product:Product,categoryId:number){
    return this.http.post<ApiResponse>(this.endpoint+"/product/add-product/"+categoryId,product)
  }

  updateProduct(product:Product, categoryId:number) {
    return this.http.put<ApiResponse>(this.endpoint+"/product/update-product/"+categoryId, product)
  }

  deleteProduct(productId: number) {
    return this.http.delete<ApiResponse>(this.endpoint+"/product/delete-product/"+productId);
  }

}
