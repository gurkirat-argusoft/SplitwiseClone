import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HomeComponent } from "./Components/home/home.component";
import { UserService } from './Services/user.service';
import { User } from './Entites/user';
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HomeComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Frontend';
  users:Set<User>=new Set();
  constructor(private userService:UserService){
    userService.getAllUsers().subscribe((data)=>{
      this.users=data;
      
    });
    this.userService.user$.next(this.users)
  }
}
