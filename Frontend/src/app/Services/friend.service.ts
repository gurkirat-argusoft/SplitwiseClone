import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../Entites/user';
import { Constants } from '../Components/Constants/Constants';

@Injectable({
  providedIn: 'root'
})
export class FriendService {

  private baseUrl = Constants.FRIEND_API; // Adjust as necessary

  constructor(private http: HttpClient) {}

  addFriend(userId: number, friendId: number): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}/addfriend/${userId}/${friendId}`, {});
  }

  getAllFriends(userId: number): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}/getallfriends/${userId}`);
  }

  deleteFriend(userId: number, friendId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${userId}/${friendId}`);
  }}
