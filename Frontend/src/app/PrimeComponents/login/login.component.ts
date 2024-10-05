import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../../Services/user.service';
import { User } from '../../Entites/user';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  user :any={
    userName:'',
    password:''
  }

  route=inject(Router);
  http=inject(HttpClient);
  constructor(private userService:UserService){}
  onLogin() {
    // Call the user service to get the user by username
    this.userService.getUserByName(this.user.userName).subscribe({
      next: (loginData) => {
        if (loginData) { // Check if user exists
          if (loginData.password === this.user.password) {
            localStorage.setItem('loginUser', loginData.userId.toString());
            this.route.navigateByUrl('home'); // Navigate to home on successful login
          } else {
            alert('Invalid password');
            // Optionally show an error message to the user
          }
        } else {
          alert('User not found');
          // Optionally show an error message to the user
        }
      },
      error: (err) => {
        alert('Error fetching user data:');
        // Handle error appropriately, e.g., show a message to the user
      }
    });
  }

}
