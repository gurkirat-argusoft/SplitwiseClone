import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Transaction } from '../Entites/transaction';
import { Constants } from '../Components/Constants/Constants';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  private apiUrl = Constants.TRANSACTION_API;

  constructor(private http: HttpClient) {}

  // Get all transactions
  getTransactions(): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(this.apiUrl);
  }

  // Get a transaction by ID
  getTransactionById(id: number): Observable<Transaction> {
    return this.http.get<Transaction>(`${this.apiUrl}/${id}`);
  }

  // Create a new transaction
  createTransaction(transaction: Transaction): Observable<Transaction> {
    return this.http.post<Transaction>(this.apiUrl, transaction);
  }

  // Update an existing transaction
  updateTransaction(transaction: Transaction): Observable<Transaction> {
    return this.http.put<Transaction>(`${this.apiUrl}/${transaction.transactionId}`, transaction);
  }

  // Delete a transaction
  deleteTransaction(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
