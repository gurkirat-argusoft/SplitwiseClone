import { CommonModule } from '@angular/common';
import { Component, ElementRef, inject, OnInit, ViewChild, viewChild } from '@angular/core';
import { GroupService } from '../../../Services/group.service';
import { User } from '../../../Entites/user';
import { Group } from '../../../Entites/group';
import { ActivatedRoute, Router, RouterLink, RouterModule } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserService } from '../../../Services/user.service';
@Component({
  selector: 'app-group-page',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterModule,ReactiveFormsModule],
  templateUrl: './group-page.component.html',
  styleUrl: './group-page.component.css'
})
export class GroupPageComponent implements OnInit {
  isModalOpen: boolean=false;
  constructor(private groupService: GroupService, private route: ActivatedRoute,private fb: FormBuilder ,private userService:UserService) { }
  groupMembers: Set<User> = new Set<User>();
  userGroups: Set<Group> = new Set<Group>();
  userId: any = localStorage.getItem('loginUser');
  groupId!: number;
  currentGroup!:Group;
  groupForm!: FormGroup;
  users:Set<User>=new Set<User>;
router=inject(Router)

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
    this.getCurrentGroup(groupId);
    this.getUserGroups();
  }

  getCurrentGroup(groupId: number): void {
    this.groupService.getGroupById(groupId).subscribe(
        (group: Group) => {
            this.currentGroup = group ; 
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
    this.groupForm = this.fb.group({
      groupName: ['', Validators.required],
      imageUrl: ['', [Validators.required, Validators.pattern('https?://.+')]] // Basic URL validation
    });
  
  }

  onUpdateGroup(): void {
   this.groupForm.patchValue({
      groupName: this.currentGroup.groupName,
      imageUrl: this.currentGroup.imageUrl
   })


  }
  onSubmit() {
   this.groupService.updateGroup(this.currentGroup.groupId,this.groupForm.value).subscribe((data:Group)=>{
    this.currentGroup=data;
    console.log(this.currentGroup)
   })
  }



  resetForm(): void {
    this.groupForm.reset(); // Reset the form to its initial state
  }

  deleteGroup(groupId:number){
    const a =confirm('are you sure you want to delete the group ?');
    if(a)
      this.groupService.deleteGroup(groupId).subscribe(()=>{
        
      })
      this.router.navigateByUrl('group')
  }
  addMember(){
    this.getAllUsers();
  }
  getAllUsers(){
      this.userService.getAllUsers().subscribe((data)=>{
        this.users=data;
      })
  }

  addMemberToGroup(userName:string){  
      this.groupService.addMember(this.currentGroup.groupId,userName).subscribe(()=>{
        this.loadUsersInGroup(this.currentGroup.groupId);
      });
  }
  deleteMember(userId: number) {
    this.groupService.deleteMember(this.currentGroup.groupId, userId).subscribe(
        (response) => {
            this.loadUsersInGroup(this.currentGroup.groupId); // Reload users after deletion
        },
        (error) => {
            console.error('Error deleting member:', error);
        }
    );
}
}
