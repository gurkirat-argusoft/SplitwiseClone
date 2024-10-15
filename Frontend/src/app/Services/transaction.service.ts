import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Transaction } from '../Entites/transaction';
import { Constants } from '../Components/Constants/Constants';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  private baseUrl = Constants.TRANSACTION_API;

  constructor(private http: HttpClient) {}

  // Add a new transaction
  addTransaction(transaction: Transaction): Observable<Transaction> {
    return this.http.post<Transaction>(`${this.baseUrl}/add`, transaction);
  }

  // Get all transactions for a user
  getAllTransactionsByUser(userId: number): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(`${this.baseUrl}/getAll/${userId}`);
  }

  // Get transactions by receiver and giver
  getTransactionsByReceiver(giverId: number, receiverId: number): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(`${this.baseUrl}/getbyreceiver/${giverId}/${receiverId}`);
  }

  // Get user transactions by event
  getUserTransactionsByEvent(giverId: number, eventId: number): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(`${this.baseUrl}/getusertransactionbyevent/${giverId}/${eventId}`);
  }

  // Get all transactions by event ID
  getTransactionsByEvent(eventId: number): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(`${this.baseUrl}/getalltransactionbyevent/${eventId}`);
  }
}
