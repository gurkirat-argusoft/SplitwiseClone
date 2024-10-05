import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { GroupService } from '../../../Services/group.service';
import { User } from '../../../Entites/user';
import { Group } from '../../../Entites/group';
import { ActivatedRoute, RouterLink, RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-group-page',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterModule,ReactiveFormsModule],
  templateUrl: './group-page.component.html',
  styleUrl: './group-page.component.css'
})
export class GroupPageComponent implements OnInit {
  constructor(private groupService: GroupService, private route: ActivatedRoute) { }
  groupMembers: Set<User> = new Set<User>();
  userGroups: Set<Group> = new Set<Group>();
  userId: any = localStorage.getItem('loginUser');
  groupId!: number;
  currentGroup!:Group;
  // getGroupId(){
  //   this.groupService.groupId$.subscribe((data)=>{
  //     debugger
  //     this.groupId=data;
  //   })
  // }
  loadUsersInGroup(groupId: number): void {
    this.groupService.getUsersInGroup(groupId).subscribe(
      (data) => {
        this.groupMembers = data;

      },
      (error) => {
        console.error('Error fetching users in group', error);
      }
    );
  }
  getUserGroups() {
    this.groupService.getUserGroups(this.userId).subscribe((data) => {
      this.userGroups = data;
    })
  }
  onGroupClick(groupId:number){
    this.loadUsersInGroup(groupId);
  }

  getCurrentGroup(groupId: number): void {
    this.groupService.getGroupById(groupId).subscribe(
        (group: Group) => {
            this.currentGroup = group; // Assign the emitted value to currentGroup
        },
        (error) => {
            console.error('Error fetching group:', error);
        }
    );
}
  ngOnInit(): void {

    this.route.params.subscribe((param) => {
      this.groupId = +param['id'];
    })
    this.getCurrentGroup(this.groupId);
    this.loadUsersInGroup(this.groupId);
    this.getUserGroups();
  }


  updateGroup(){
    console.log('working');
    
  }
}
