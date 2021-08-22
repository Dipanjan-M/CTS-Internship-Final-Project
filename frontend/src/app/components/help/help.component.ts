import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';
import { AdminService } from 'src/app/services/admin.service';
import { AuthenticationService } from 'src/app/services/authentication.service';

import { RaiseIssueService } from 'src/app/services/raise-issue.service';

@Component({
  selector: 'app-help',
  templateUrl: './help.component.html',
  styleUrls: ['./help.component.css']
})
export class HelpComponent implements OnInit {

  resolveIssue: Boolean = false;
  resp: any = {
    issueId: Number, response: String
  };
  empId?: number;
  issueId: any;
  dataSource: any;
  adminIssueHeaders = ["Id", "Title", "Description", "Solved", "Response", "Created At", "Last Update", "Action"]
  issueHeaders = ["Id", "Title", "Description", "Solved", "Response", "Created At", "Last Update"]
  constructor(
    private issueService: RaiseIssueService,
    private adminService: AdminService,
    private authService: AuthenticationService
  ) {
    this.empId = authService.currentUserValue.id;
  }

  ngOnInit(): void {
    if (this.isAdmin()) {
      this.getIssues()
    } else {
      this.getIssueByEmpId(this.empId)
    }
  }

  isAdmin() {
    return this.adminService.isAdmin();
  }

  resolve(response: any) {
    //this.issueService.resolveIssue(response)
    this.resolveIssue = !this.resolveIssue;
    console.log(response)
    this.issueId = response;
  }

  submit1(f: any) {
    console.log(f.value)
    this.issueService.resolveIssue(f.value).pipe(first())
      .subscribe(
        res => {
          console.log(res);
        }
      )
    window.location.reload();
  }

  submit(f: any) {
    console.log(f.value);
    this.issueService.postIssue(f.value).pipe(first())
      .subscribe(
        res => {
          if (res.status == 200) {
            alert(res.message);
          }
        },
        error => {
          console.log(error);
        }
      );
    window.location.reload();
  }

  getIssues() {
    this.issueService.getAllIssuesByAdmin().pipe(first())
      .subscribe(res => {
        if (res.status == 200) {
          this.dataSource = res.data
        }
        else {
          alert(res.message)
        }
      },
        error => {
          console.log(error)
        }

      )
  }

  getIssueByEmpId(empId: any) {
    this.issueService.getIssueByEmpId(empId).pipe(first())
      .subscribe(
        res => {
          console.log(res)
          if (res.status == 200) {
            this.dataSource = res.data
          }
          else {
            alert(res.message)
          }
        },
        error => {
          console.log(error)
        }
      )
  }

  deleteIssue(id: number) {
    this.issueService.deleteIssue(id).pipe(first())
      .subscribe(
        res => {
          alert(res.message);
          this.getIssues();
        },
        error => {
          console.log(error)
        }
      )
  }
}
