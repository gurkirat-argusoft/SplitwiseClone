import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { GroupService } from '../../../Services/group.service';
import { User } from '../../../Entites/user';

@Component({
  selector: 'app-group-page',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './group-page.component.html',
  styleUrl: './group-page.component.css'
})
export class GroupPageComponent implements OnInit {
  constructor(private groupService:GroupService){}
  groupMembers:Set<User>=new Set<User>();
  usersInGroup(){
    this.groupService.usersInGroup$.subscribe((data)=>{
        this.groupMembers=data;
        console.log(this.groupMembers)
    })
  }
ngOnInit(): void {
    this.usersInGroup();
}

}
