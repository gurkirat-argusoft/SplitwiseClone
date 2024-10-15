import { Component, OnInit } from '@angular/core';
import { EventService } from '../../Services/event.service';
import { User } from '../../Entites/user';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-event',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './event.component.html',
  styleUrl: './event.component.css'
})
export class EventComponent implements OnInit {
  events: Event[] = []
  members:User[]=[]
  userId:any=localStorage.getItem('loginUser')
  constructor(private eventService: EventService) { }

  getAllEventsByUser(){
    this.eventService.getEventsByUser(this.userId).subscribe((data:Event[])=>{
      this.events=data
      this.
    })
  }
  getUsersInEvent(eventId:number){
    this.eventService.getEventMembers(eventId).subscribe(
      (members: Set<User>) => {
        const event = Array.from(this.events).find(g => g.eventId === eventId);
        if (event) {
          event.members = members;
        }
      },
      (error) => {
        console.error('Error fetching users in group', error);
      }
    );
    })
  }
  ngOnInit(): void {
      this.getAllEventsByUser();

  }
}
