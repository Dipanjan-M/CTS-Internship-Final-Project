import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ApiResponse } from 'src/app/models/response';


@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private http:HttpClient) { }

  postNotification(notificationData:any){
    
    return this.http.post<ApiResponse>(`${environment.apiUrl}/admin/notice`,notificationData)
  }

  getNotification() {
    return this.http.get<ApiResponse>(`${environment.apiUrl}/admin/notice`)
  }
  getEmployeeNotification() {
    return this.http.get<ApiResponse>(`${environment.apiUrl}/employee/notice/scope/`+0)
  }

  deleteNotification(id:any) {
    return this.http.delete<ApiResponse>(`${environment.apiUrl}/admin/notice/`+id)
  }
}
