import { Component } from '@angular/core';
import { RouterLink, RouterModule, RouterOutlet } from '@angular/router';
import { Group } from '../../../Entites/group';
import { GroupService } from '../../../Services/group.service';
import { CommonModule } from '@angular/common';
import { User } from '../../../Entites/user';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-group',
  standalone: true,
  imports: [RouterLink, RouterModule, RouterOutlet, CommonModule,FormsModule],
  templateUrl: './group.component.html',
  styleUrl: './group.component.css'
})
export class GroupComponent {
  groups: Set<Group> = new Set();
  filteredGroups: Set<Group> = new Set();
  userId: any = localStorage.getItem('loginUser');
  searchInput: string = '';
  noResultsFound: boolean = false; // New property to track search results

  constructor(private groupService: GroupService) {}

  ngOnInit(): void {
    this.loadUserGroups(Number(localStorage.getItem('loginUser')));
  }

  loadUserGroups(userId: number): void {
    this.groupService.getUserGroups(userId).subscribe(
      (data: Set<Group>) => {
        this.groups = data;
        this.filteredGroups = new Set(data);
        this.noResultsFound = false; // Reset on load
        data.forEach(group => {
          this.loadUsersInGroup(group.groupId);
        });
      },
      (error: any) => {
        console.error('Error fetching user groups', error);
      }
    );
  }

  loadUsersInGroup(groupId: number): void {
    this.groupService.getUsersInGroup(groupId).subscribe(
      (users: Set<User>) => {
        const group = Array.from(this.groups).find(g => g.groupId === groupId);
        if (group) {
          group.users = users;
        }
      },
      (error) => {
        console.error('Error fetching users in group', error);
      }
    );
  }

  searchGroups(): void {
    if (this.searchInput) {
      const searchLower = this.searchInput.toLowerCase();
      const filteredArray = Array.from(this.groups).filter(group =>
        group.groupName.toLowerCase().includes(searchLower)
      );
      this.filteredGroups = new Set(filteredArray);
      this.noResultsFound = filteredArray.length === 0; // Update noResultsFound based on filter
    } else {
      this.filteredGroups = new Set(this.groups);
      this.noResultsFound = false; // Reset if input is empty
    }
  }
  reload(){
    this.noResultsFound=false;
    this.searchInput='';
    this.loadUserGroups(Number(localStorage.getItem('loginUser')));
  }
}
