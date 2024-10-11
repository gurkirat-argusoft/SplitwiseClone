// add-expense.component.ts
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EventService } from '../../../Services/event.service';
import { Event } from '../../../Entites/event';;

@Component({
  selector: 'app-add-expense',
  templateUrl: './add-expense.component.html',
  styleUrl: './add-expense.component.css',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule]
})
export class AddExpenseComponent implements OnInit {
  @Output() closeDialog = new EventEmitter<void>();
  @Output() expenseAdded = new EventEmitter<any>();

  @Input() expenseType:string="";

  event!:Event;
  expenseForm!: FormGroup;
  groups: string[] = [
    "Family",
    "Friends",
    "Work Colleagues",
    "Book Club",
    "Gym Buddies",
    "Travel Enthusiasts",
    "Cooking Group",
    "Game Night Crew",
    "Movie Lovers",
    "Art Class",
    "Sports Team",
    "Volunteer Group",
    "Study Group",
    "Hiking Club",
    "Photography Group",
    "Music Band",
    "Crafting Circle",
    "Tech Enthusiasts",
    "Startup Group",
    "Pet Owners",
    "Foodies"
  ];

  friends: string[] = [
    "Alice Johnson",
    "Bob Smith",
    "Charlie Brown",
    "David Wilson",
    "Eve Davis",
    "Frank Miller",
    "Grace Lee",
    "Hannah White",
    "Isaac Harris",
    "Jasmine Clark",
    "Kevin Lewis",
    "Liam Robinson",
    "Mia Hall",
    "Noah Young",
    "Olivia King",
    "Paul Wright",
    "Quinn Scott",
    "Rita Adams",
    "Sam Baker",
    "Tina Gonzalezzzzzzzzzzzzzzzzzzzzzzzzz",
    "Uma Patel"
  ];
  
  selectedFriends: string[] = [];
  constructor(private fb: FormBuilder,private eventService:EventService) { }

  ngOnInit() {
    this.expenseForm = this.fb.group({
      amount: ['', [Validators.required, Validators.min(0)]],
      description: ['', Validators.required],
      date: ['', Validators.required],
      expenseType: ['', Validators.required],
      group: [''],
      friends: [[]],
      event: ['']
    });
  }

  onSubmit() {
    //logic to perform after form submission
    if (this.expenseForm.valid) {
      let expense = this.expenseForm.value;
      this.event = new Event(20,expense['description'],expense['date'],expense['amount'],'even',expense['group']);
      console.log(this.event);
    }
    //this.eventService.addEvent(this.event);
    //to close the modal
    this.closeDialog.emit();
  }

  onCancel() {
    this.closeDialog.emit();
  }

  onFriendsSelect(event: any) {

    const selectElement = event.target as HTMLSelectElement;
    const options = selectElement.options;
    // const options = event.target.options;
    let currentSelectedOptions = Array.from(options)
      .filter((option: HTMLOptionElement) => option.selected)
      .map((option: HTMLOptionElement) => option.value);

    this.friends = Array.from(options)
      .filter((option: HTMLOptionElement) => !option.selected)
      .map((option: HTMLOptionElement) => option.value);

    this.selectedFriends.push(...currentSelectedOptions);
  }

  removeFriend(index: number, friendName: string) {
    this.selectedFriends.splice(index, 1);
    this.friends.push(friendName);
  }
}