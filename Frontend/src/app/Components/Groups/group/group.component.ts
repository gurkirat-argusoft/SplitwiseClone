import { Component } from '@angular/core';
import { RouterLink, RouterModule, RouterOutlet } from '@angular/router';
import { Group } from '../../../Entites/group';
import { GroupService } from '../../../Services/group.service';
import { CommonModule } from '@angular/common';
import { User } from '../../../Entites/user';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserService } from '../../../Services/user.service';

@Component({
  selector: 'app-group',
  standalone: true,
  imports: [RouterLink, RouterModule, RouterOutlet, CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './group.component.html',
  styleUrl: './group.component.css'
})
export class GroupComponent {
  groupForm: FormGroup = new FormGroup({
    groupName: new FormControl("",[Validators.required]),
    imageUrl: new FormControl("")
  });
  groups: Set<Group> = new Set();
  filteredGroups: Set<Group> = new Set();
  userId: any = localStorage.getItem('loginUser');
  searchInput: string = '';
  noResultsFound: boolean = false; 
  users: Set<User> = new Set<User>();
  user!: User;
  constructor(private groupService: GroupService, private userService: UserService) { }

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

  reload() {
    this.noResultsFound = false;
    this.searchInput = '';
    this.loadUserGroups(Number(localStorage.getItem('loginUser')));
  }

  getFirstFourUsers(users?: Set<User>): User[] {
    return Array.from(users || []).slice(0, 4);
  }

  openGroupModal() {
    this.userService.getUserById(Number(localStorage.getItem('loginUser'))).subscribe((data: User) => {
      this.user = data;
      console.log(this.user.userName);
    });
  }

  onSubmit() {
    this.groupService.createGroup(this.groupForm.value).subscribe((data) => {
      console.log(data.groupId);
      this.groupService.addMember(data.groupId, this.user.userName).subscribe(
        (response) => {
          this.loadUserGroups(Number(localStorage.getItem('loginUser')));
          this.groupForm.reset();
        },
        (error) => {
          console.error('Error adding member:', error);
        }
      );
    })
  }

  resetForm() {
    this.groupForm.reset(); // Reset the form to its initial state

  }


}
