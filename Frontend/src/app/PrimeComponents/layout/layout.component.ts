import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-layout',
  standalone: true,
  imports: [RouterLink,RouterOutlet,CommonModule,FormsModule],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.css'
})
export class LayoutComponent {
route=inject(Router);
  onLogout(){
    localStorage.removeItem('loginUser');
    this.route.navigateByUrl('login');
  }
}
