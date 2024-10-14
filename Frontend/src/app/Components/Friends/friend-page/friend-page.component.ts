import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink, RouterModule } from '@angular/router';
import { FriendService } from '../../../Services/friend.service';
import { User } from '../../../Entites/user';
import { CommonModule } from '@angular/common';
import { UserService } from '../../../Services/user.service';

@Component({
  selector: 'app-friend-page',
  standalone: true,
  imports: [CommonModule,RouterLink,RouterModule],
  templateUrl: './friend-page.component.html',
  styleUrl: './friend-page.component.css'
})
export class FriendPageComponent implements OnInit{
constructor(private route :ActivatedRoute,private friendService:FriendService,private userService:UserService,private router:Router){}
friends: Set<User> = new Set(); // Use Set to manage unique friends
currentFriend!:User;
friendId!:number;
userId:any=localStorage.getItem('loginUser');

ngOnInit(): void {
  this.route.params.subscribe((param) => {
    this.friendId = +param['id'];

  })
  console.log('this'+this.userId);
  this.getCurrentFriend();
  this.getAllFriends()
  
}
getCurrentFriend(){
      this.userService.getUserById(this.friendId).subscribe((data)=>{
        this.currentFriend=data;
      })
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

onFriendClick(){
  this.getCurrentFriend();
}

deleteFriend(friendId:number){
  const a = confirm('Are you sure you want to remove'+this.currentFriend.userName+'as friend');
  if(a){
    this.friendService.deleteFriend(this.userId,this.currentFriend.userId).subscribe();
    this.router.navigateByUrl('friend')}
}
}
