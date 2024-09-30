import { Component } from '@angular/core';
import { RouterLink, RouterModule, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-group',
  standalone: true,
  imports: [RouterLink,RouterModule,RouterOutlet],
  templateUrl: './group.component.html',
  styleUrl: './group.component.css'
})
export class GroupComponent {

}
