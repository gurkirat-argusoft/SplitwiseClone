import { CommonModule } from '@angular/common';
import { Component, inject, ViewChild } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { AddExpenseComponent } from '../../Components/Expenses/add-expense/add-expense.component';

@Component({
  selector: 'app-layout',
  standalone: true,
  imports: [RouterLink, RouterOutlet, CommonModule, FormsModule, AddExpenseComponent],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.css'
})
export class LayoutComponent {
  route = inject(Router);

  @ViewChild(AddExpenseComponent) model!: AddExpenseComponent;

  openModal() {
    // You can use jQuery or other methods to trigger the modal
    $('#addExpenseModel').modal('show');
  }
  onLogout() {
    localStorage.removeItem('loginUser');
    this.route.navigateByUrl('login');
  }

  saveChanges() {
    // Implement your save logic here
    console.log('Changes saved!');
    $('#addExpenseModel').modal('hide'); // Optionally close the modal
  }

  cancel() {
    // Logic for canceling can go here
    console.log('Changes canceled!');
    $('#addExpenseModel').modal('hide'); // Optionally close the modal
  }
}
