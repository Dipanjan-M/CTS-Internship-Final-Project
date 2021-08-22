import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';

import { NotificationService } from 'src/app/services/notification.service';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent implements OnInit {

  notificationData:any;
  notificationEmployeeData:any;

  notification ={
    subject: '',
    body: '',
    scope: 0
  }

  constructor(private service: NotificationService, private adminService: AdminService) { }

  ngOnInit(): void {
    this.notificationData = this.getNotificationData();
    this.notificationEmployeeData = this.getNotificationEmployeeData();
    console.log(this.notificationData)
  }

  isAdmin() {
    return this.adminService.isAdmin();
   }

  getNotificationData(){
    this.service.getNotification()
      .pipe(first())
      .subscribe(
        (res) => {
          console.log(res)
          this.notificationData =  res.data;
        },
        err => console.log(err)
      )
  }
  getNotificationEmployeeData(){
    this.service.getEmployeeNotification()
      .pipe(first())
      .subscribe(
        (res) => {
          console.log(res)
          this.notificationEmployeeData =  res.data;
        },
        err => console.log(err)
      )
  }

  submitNotification() {
    this.service.postNotification(this.notification)
      .pipe(first())
      .subscribe(
        (res) => {
          console.log(res);
          window.location.reload();
        },
        (error) => {
          console.log(error);
        }
      )
  }

  deleteNotification(id:any) {
    this.service.deleteNotification(id)
      .pipe(first())
      .subscribe(
        res =>{ 
          console.log(res)
          window.location.reload();
        },
        err => console.warn(err)     
      )
  }

}
