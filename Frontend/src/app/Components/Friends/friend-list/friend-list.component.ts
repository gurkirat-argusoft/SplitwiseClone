import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { User } from '../../../Entites/user';
import { FriendService } from '../../../Services/friend.service';
import { UserService } from '../../../Services/user.service';

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
  users:Set<User>=new Set();
  constructor(private friendService: FriendService,private userService:UserService) {}

  ngOnInit(): void {
     // Replace with actual user ID
    this.getAllFriends();
    console.log(this.userId);
    this.getAllUsers();
  }

  getAllFriends(): void {
    this.friendService.getAllFriends(this.userId).subscribe(
      (data) => {
        this.friends = new Set(data); // Convert array to Set
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
  getAllUsers(){
    this.userService.getAllUsers().subscribe((data)=>{
      this.users=data;
    })
  }
}
