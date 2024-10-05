import { Component } from '@angular/core';
import { RouterLink, RouterModule, RouterOutlet } from '@angular/router';
import { Group } from '../../../Entites/group';
import { GroupService } from '../../../Services/group.service';
import { CommonModule } from '@angular/common';
import { User } from '../../../Entites/user';

@Component({
  selector: 'app-group',
  standalone: true,
  imports: [RouterLink, RouterModule, RouterOutlet, CommonModule],
  templateUrl: './group.component.html',
  styleUrl: './group.component.css'
})
export class GroupComponent {
  groups: Set<Group> = new Set();
  constructor(private groupService: GroupService) { }

  ngOnInit(): void {
    this.loadUserGroups(Number(localStorage.getItem('loginUser'))); // Replace with actual user ID
  }

  loadUserGroups(userId: number): void {
    this.groupService.getUserGroups(userId).subscribe(
      (data: Set<Group>) => { // Correctly define the parameter here
        this.groups = data; // Assign the data to this.groups
        // Fetch users for each group
        data.forEach(group => {
          this.loadUsersInGroup(group.groupId);
        },
          (error:any) => {
            console.error('Error fetching user groups', error);
          }
        );
      });}

  loadUsersInGroup(groupId: number): void {
    this.groupService.getUsersInGroup(groupId).subscribe(
      (users: Set<User>) => {
        const group = Array.from(this.groups).find(g => g.groupId === groupId);
        if (group) {
          group.users = users; // Assign fetched users to the corresponding group 
        }
      },
      (error) => {
        console.error('Error fetching users in group', error);
      }
    );
  }

  sendUserOfGroup(groupId: number): void {
    this.groupService.getUsersInGroup(groupId).subscribe(
      (users: Set<User>) => {
        // Send the fetched users to usersInGroup$
        this.groupService.usersInGroup$.next(users);
      },
      (error) => {
        console.error('Error fetching users in group', error);
      }
    );
  }

  onRoute(groupId:number){
    this.sendUserOfGroup(groupId);
  }
}
