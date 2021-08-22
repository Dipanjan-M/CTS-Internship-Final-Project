import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ApiResponse } from 'src/app/models/response';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  
  customURL = environment.apiUrl;

  constructor(private http: HttpClient) { }

  


}
