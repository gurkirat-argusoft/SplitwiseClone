import { CommonModule, DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { Transaction } from '../../../Entites/transaction';
import { TransactionService } from '../../../Services/transaction.service';
import { UserService } from '../../../Services/user.service';
import { User } from '../../../Entites/user';

@Component({
  selector: 'app-all-transactions',
  standalone: true,
  imports: [CommonModule,DatePipe],
  templateUrl: './all-transactions.component.html',
  styleUrl: './all-transactions.component.css'
})
export class AllTransactionsComponent { 
  transactions: Transaction[] = [];
  users: Map<number, User> = new Map(); // Store users by ID
  userId: any = localStorage.getItem('loginUser');
  sortMethod: string = 'newest'; 
  constructor(private transactionService: TransactionService, private userService: UserService) {}

  ngOnInit() {
    this.getAllUsers(); // Fetch all users first
    this.getAllTransactionsOfUser(); // Then fetch transactions
  }

  getAllUsers() {
    this.userService.getAllUsers().subscribe((data) => {
      data.forEach(user => {
        this.users.set(user.userId, user); // Store users in a Map for quick access
      });
    });
  }
  sortTransactions() {
    if (this.sortMethod === 'newest') {
      this.transactions.sort((a, b) => new Date(b.doneAt).getTime() - new Date(a.doneAt).getTime());
    } else {
      this.transactions.sort((a, b) => new Date(a.doneAt).getTime() - new Date(b.doneAt).getTime());
    }
  }

  onSortChange(event: Event) {
    const selectElement = event.target as HTMLSelectElement;
    this.sortMethod = selectElement.value;
    this.sortTransactions(); // Sort transactions based on the selected method
  }


  getAllTransactionsOfUser() {
    this.transactionService.getAllTransactionsByUser(this.userId).subscribe((data) => {
      this.transactions = data;   
      this.sortTransactions(); // Sort transactions after fetching
    });
  }

  getGiver(giverId: number): string {
    const giver = this.users.get(giverId);
    return giver ? giver.userName : 'Unknown'; // Return 'Unknown' if not found
  }

  getReceiver(receiverId: number): string {
    const receiver = this.users.get(receiverId);
    return receiver ? receiver.userName : 'Unknown'; // Return 'Unknown' if not found
  }
}
