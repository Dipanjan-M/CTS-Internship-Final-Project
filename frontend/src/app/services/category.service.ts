import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponse } from 'src/app/models/response';
import { Category } from 'src/app/models/category';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  endpoint = environment.apiUrl;
  constructor(private http: HttpClient) { }

  getCategorys(): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.endpoint+'/category/get-all-categories');
  }

  postCategory(category:Category){
    return this.http.post<ApiResponse>(this.endpoint+"/category/create-category",category)
  }

  updateCategories(category:Category, categoryId:number){
    return this.http.put<ApiResponse>(this.endpoint+"/category/update-category/"+categoryId,category)
  }

  deleteCategory(categoryId:number){
    return this.http.delete<ApiResponse>(this.endpoint+"/category/delete/"+categoryId)
  }
}
