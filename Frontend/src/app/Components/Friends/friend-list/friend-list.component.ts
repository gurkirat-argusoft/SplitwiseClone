import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { User } from '../../../Entites/user';
import { FriendService } from '../../../Services/friend.service';
import { UserService } from '../../../Services/user.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-friend-list',
  standalone: true,
  imports: [RouterLink, FormsModule,CommonModule],
  templateUrl: './friend-list.component.html',
  styleUrl: './friend-list.component.css'
})
export class FriendListComponent {
  friends: Set<User> = new Set(); // Use Set to manage unique friends
  filteredFriends: Set<User> = new Set(); // To hold filtered friends
  addfilteredFriends: Set<User> = new Set(); // To hold filtered friends
  userId: any = localStorage.getItem('loginUser');
  users: Set<User> = new Set();
  searchTerm: string = ''; // Variable to hold the search term
  addsearchTerm: string = ''; 
  noResultsFound: boolean = false; // Flag for no results found
  anyResult:boolean=false
  constructor(private friendService: FriendService, private userService: UserService) { }

  ngOnInit(): void {
    this.getAllFriends();
    this.getAllUsers();
  }

  getAllFriends(): void {
    this.friendService.getAllFriends(this.userId).subscribe(
      (data) => {
        this.friends = new Set(data); // Convert array to Set
        this.filteredFriends = new Set(this.friends);
      },
      (error) => {
        console.error('Error fetching friends:', error);
      }
    );
  }

  addFriend(friendId: number): void {
    // Replace with actual user ID
    this.friendService.addFriend(this.userId, friendId).subscribe(
      (data) => {
        this.friends.add(data); // Add friend to Set
        console.log('Friend added:', data);
        this.getAllFriends()
      },
      (error) => {
        console.error('Error adding friend:', error);
      }
    );
  }

  deleteFriend(friendId: number): void {
    const userId = 1; // Replace with actual user ID
    this.friendService.deleteFriend(userId, friendId).subscribe(
      () => {
        this.friends.forEach(friend => {
          if (friend.userId === friendId) {
            this.friends.delete(friend); // Remove friend from Set
          }
        });
        console.log('Friend deleted');
      },
      (error) => {
        console.error('Error deleting friend:', error);
      }
    );
  }
  getAllUsers() {
    this.userService.getAllUsers().subscribe((data) => {
      this.users = data;
      this.addfilteredFriends=this.users
    })
  }

  // New method for searching friends
  searchFriends(): void {
    console.log(this.searchTerm);
    
    if (this.searchTerm) {
      const searchLower = this.searchTerm.toLowerCase();
      const filteredArray = Array.from(this.friends).filter(friend =>
        friend.userName.toLowerCase().includes(searchLower)
      );
      this.filteredFriends = new Set(filteredArray);
      this.noResultsFound = filteredArray.length === 0; // Update noResultsFound based on filter
    } else {
      this.filteredFriends = new Set(this.friends); // Reset if input is empty
      this.noResultsFound = false; // Reset flag
    }
  }
  reload() {
    this.noResultsFound = false;
    this.searchTerm = '';
this.getAllFriends()  }

  // New method for searching friends
  addsearchFriends(): void {
    
    if (this.addsearchTerm) {
      const searchLower = this.addsearchTerm.toLowerCase();
      const filteredArray = Array.from(this.users).filter(friend =>
        friend.userName.toLowerCase().includes(searchLower)
      );
      this.addfilteredFriends = new Set(filteredArray);
      this.anyResult = filteredArray.length === 0; // Update noResultsFound based on filter
    } else {
      this.addfilteredFriends = new Set(this.users); // Reset if input is empty
      this.anyResult = false; // Reset flag 
    }
  }
  reloadUsers() {
    this.anyResult = false;
    this.addsearchTerm = '';
this.getAllUsers()  }
}
