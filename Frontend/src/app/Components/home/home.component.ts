import { CommonModule, CurrencyPipe } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { GroupPageComponent } from "../Groups/group-page/group-page.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CurrencyPipe, CommonModule, FormsModule, GroupPageComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
