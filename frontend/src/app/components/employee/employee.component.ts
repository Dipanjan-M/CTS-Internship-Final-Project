import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';

import { NewUser } from 'src/app/models/newUser';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { EmployeeService } from 'src/app/services/employee.service';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  user: any;

  existingUsers: any;

  hasNewUsers: boolean = false;
  errorMsg?: string;

  constructor(
    private service: EmployeeService,
    private authService: AuthenticationService
  ) { }

  ngOnInit(): void {
    this.getAllNewUsers();
    this.getAllUsers();
  }

  isCurrentUser(id: number) {
    return this.authService.currentUserValue.id == id;
  }

  getAllUsers() {
    this.service.getAllUsers().pipe(first())
      .subscribe(
        res => {
          if (res.status == 200) {
            this.existingUsers = res.data;
          } else {
            alert(res.message);
          }
        },
        error => {
          console.log(error);
        }
      )
  }

  getAllNewUsers() {
    this.service.getNewUsers()
      .pipe(first())
      .subscribe(
        (res) => {
          if (res.status == 200) {
            this.user = res.data;
            this.hasNewUsers = true;
          } else {
            this.hasNewUsers = false;
            this.errorMsg = res.message;
          }
        },
        (err) => {
          console.log(err)
        }
      )
  }

  givePermission(id: any) {
    console.log(id)
    this.service.givePermission(id)
      .pipe(first())
      .subscribe(
        (res) => {
          if (res.status == 200) {
            alert("Permission Granted")
          }
          this.getAllNewUsers();
          this.getAllUsers();
        },
        (err) => {
          console.log(err);
        }
      )
  }

}
