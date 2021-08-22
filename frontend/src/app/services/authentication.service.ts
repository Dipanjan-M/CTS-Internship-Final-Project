import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { environment } from 'src/environments/environment';
import { User } from './../models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;

 

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')!));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  loggedIn(){
    const currentUser = this.currentUserValue;
    return !!currentUser && currentUser.token;
  }

  login(userName: string, password: string) {
    return this.http.post<any>(`${environment.apiUrl}/authenticate`, { userName, password })
    .pipe(map(response => {
      console.log(response);
      if(response.status != 200) {
        return response;
      }
      // login successful if there's a jwt in the response
      if (response.data && response.data.token && response.status == 200) {
        // store user details and jwt in local storage to keep user logged in between page refreshes
        localStorage.setItem('currentUser', JSON.stringify(response.data));
        this.currentUserSubject.next(response.data);
      }

      return response.data;
    }));
  }

  register(registerData:any) {
    return this.http.post<any>(`${environment.apiUrl}/user/create`, registerData)
    .pipe(map(response => {
      console.log(response);
      // login successful if there's a jwt token in the response
      if(response.status == 500) {
        return response;
      }
      if (response.data && response.data.token && response.status == 200) {
        // store user details and jwt token in local storage to keep user logged in between page refreshes
        localStorage.setItem('currentUser', JSON.stringify(response.data));
        this.currentUserSubject.next(response.data);
      }

      return response.data;
    }));
  }
  adminRegister(registerData:any) {
    return this.http.post<any>(`${environment.apiUrl}/admin/create`, registerData)
   }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null!);
    return true;
  }

  
}

