import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/services/admin.service';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor( private service: AdminService, private authenticationService: AuthenticationService, private router: Router) { }

  ngOnInit(): void {
  }

  logout() {
    if(this.authenticationService.logout()) {
      this.router.navigate(['/login']);
    }
  }

  isLoggedin() {
    return this.authenticationService.loggedIn();
  }
  notLoggedIn() {
    return !this.authenticationService.loggedIn();
  }

  isAdmin() {
   return this.service.isAdmin();
  }

}
