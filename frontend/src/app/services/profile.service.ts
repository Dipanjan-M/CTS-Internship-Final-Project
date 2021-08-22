import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { ApiResponse } from 'src/app/models/response';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private http:HttpClient) { }

  updatePassword(id:any, passwordData:any) {
    return this.http.put<any>(`${environment.apiUrl}/change-password/`+id, passwordData);
  }

}
