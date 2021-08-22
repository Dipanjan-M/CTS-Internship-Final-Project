import { Injectable } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private service: AuthenticationService) { }
  
  isAdmin() {

    const currentUser = this.service.currentUserValue;
    if(currentUser) {  
      //console.log(currentUser.role == "Admin") 
      if(currentUser.role == "Admin") {
        return true;
      } else {
        return false;
      }
    }  
    return false;
  }
}
