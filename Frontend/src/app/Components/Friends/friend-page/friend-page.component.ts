import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FriendService } from '../../../Services/friend.service';

@Component({
  selector: 'app-friend-page',
  standalone: true,
  imports: [],
  templateUrl: './friend-page.component.html',
  styleUrl: './friend-page.component.css'
})
export class FriendPageComponent implements OnInit{
constructor(private route :ActivatedRoute,private friendService:FriendService){}

friendId!:number;
userId:any=localStorage.getItem('userlogin');

ngOnInit(): void {
  this.route.params.subscribe((param) => {
    this.friendId = +param['id'];
    console.log(this.friendId);
  })
}

getAllFriends(){
  this.friendService.getAllFriends(this.userId).subscribe
}


}
