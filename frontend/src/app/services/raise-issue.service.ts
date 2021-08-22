import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponse } from 'src/app/models/response';
import { Issue } from 'src/app/models/issue';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RaiseIssueService {

  constructor(private http: HttpClient) { }
  
  getIssueByEmpId(empId:number): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(`${environment.apiUrl}/employee/issues/`+empId);
  }

  getAllIssuesByAdmin(): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(`${environment.apiUrl}/admin/issues`);
  }

  postIssue(issue:Issue){
    return this.http.post<ApiResponse>(`${environment.apiUrl}/employee/add-new-issue`,issue);
  }

  resolveIssue(res:any){
    return this.http.put<ApiResponse>(`${environment.apiUrl}/admin/resolve-issue`,res);
  }

  deleteIssue(id: number) {
    return this.http.delete<ApiResponse>(`${environment.apiUrl}/admin/issue/delete/`+id);
  }

}
