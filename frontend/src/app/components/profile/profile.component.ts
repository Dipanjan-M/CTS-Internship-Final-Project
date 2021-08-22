import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { ProfileService } from 'src/app/services/profile.service';
import Swal from 'sweetalert2';



@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {



  flag = false
  password = {
    oldPassword: '',
    newPassword: ''
  }

  constructor(private authenticationService: AuthenticationService, private service: ProfileService) { }
  ngOnInit(): void {
  }

  toggle() {
    this.flag = true;
  }

  currentUser = this.authenticationService.currentUserValue;  
  userProfile = {
    firstName: this.currentUser.firstName,
    lastName: this.currentUser.lastName,
    role: this.currentUser.role,  
    username: this.currentUser.userName

  }



  updatePassword(){
    Swal.fire({
      title: 'Are you sure?',
      text: 'You will not be able to recover your old password!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, update it!',
      cancelButtonText: 'No, keep it'
    }).then((result) => {
      if (result.isConfirmed) {
        const currentUser = this.authenticationService.currentUserValue;
        console.log(currentUser)
        this.service.updatePassword(currentUser.id,this.password)
          .pipe(first())
          .subscribe(
            (res) => {
              console.log(res)
              if(res.status == 200) {

                Swal.fire(
                  'Success!',
                  `${res.message}`,
                  'success'
                  )
              } else {
                Swal.fire(
                  'Failure!',
                  `${res.message}`,
                  'error'
                  )
              }
              window.location.reload();
            },
            (err) => {
              Swal.fire(
                'Cancelled',
                `${err}`,
                'error'
              )
            }
          )

       
      // For more information about handling dismissals please visit
      // https://sweetalert2.github.io/#handling-dismissals
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire(
          'Cancelled',
          'Your password is not updated :)',
          'error'
        )
      }
    })
  }



}
