import { CommonModule, CurrencyPipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { GroupPageComponent } from "../Groups/group-page/group-page.component";
import { UserService } from '../../Services/user.service';
import { User } from '../../Entites/user';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CurrencyPipe, CommonModule, FormsModule, GroupPageComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{
    constructor(private userService:UserService){}
    ngOnInit(): void {
      if (this.userName) {
        this.loadCurrentUser(this.userName); // Call loadCurrentUser only if userName is not null
      } else {
        console.error("No user is logged in.");
        // Handle the case where there is no logged-in user
      }
    }
    currentUser!: User;
    userName:string=localStorage.getItem('loginUser')!;

    loadCurrentUser(name: string): void {
      this.userService.getUserByName(name).subscribe({
        next: (data) => {
          this.currentUser = data;
        },
        error: (err) => {
          console.error("Error fetching user data:", err);
          // Handle error appropriately, e.g., show a message to the user
        }
      });
    }


}
