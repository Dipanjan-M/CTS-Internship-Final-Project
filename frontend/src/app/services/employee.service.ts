import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { ApiResponse } from '../models/response';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http:HttpClient) { }

  getNewUsers() {
    return this.http.get<any>(`${environment.apiUrl}/admin/new-users`);
  }

  getAllUsers() {
    return this.http.get<ApiResponse>(`${environment.apiUrl}/admin/get-all-users`);
  }

  givePermission(id:any){
    return this.http.put<any>(`${environment.apiUrl}/admin/activate-deactivate-user/`+id,'')
  }
}
