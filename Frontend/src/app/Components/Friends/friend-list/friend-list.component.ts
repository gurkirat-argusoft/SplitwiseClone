import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { User } from '../../../Entites/user';
import { FriendService } from '../../../Services/friend.service';

@Component({
  selector: 'app-friend-list',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './friend-list.component.html',
  styleUrl: './friend-list.component.css'
})
export class FriendListComponent {
  friends: Set<User> = new Set(); // Use Set to manage unique friends
  userId :any=localStorage.getItem('loginUser');
  constructor(private friendService: FriendService) {}

  ngOnInit(): void {
     // Replace with actual user ID
    this.getAllFriends(this.userId);
    console.log(this.userId);
    
  }

  getAllFriends(userId: number): void {
    this.friendService.getAllFriends(userId).subscribe(
      (data) => {
        this.friends = new Set(data); // Convert array to Set
      },
      (error) => {
        console.error('Error fetching friends:', error);
      }
    );
  }

  addFriend(friendId: number): void {
    const userId = 1; // Replace with actual user ID
    this.friendService.addFriend(userId, friendId).subscribe(
      (data) => {
        this.friends.add(data); // Add friend to Set
        console.log('Friend added:', data);
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
          if (friend.userId=== friendId) {
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
}
