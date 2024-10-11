import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../Entites/user';
import { BehaviorSubject, Observable } from 'rxjs';
import { Constants } from '../Components/Constants/Constants';
@Injectable({
  providedIn: 'root'
})
export class UserService {
  public user$:BehaviorSubject<Set<User>>=new BehaviorSubject<Set<User>>(new Set());
  private apiUrl = Constants.USER_API ; // Update with your API URL

  constructor(private http: HttpClient) {}

  // Add a new user
  addUser(user: User): Observable<User> {
    return this.http.post<User>(`${this.apiUrl}/add`, user);
  }

  // Get all users
  getAllUsers(): Observable<Set<User>> {
    return this.http.get<Set<User>>(`${this.apiUrl}/getall`);
  }

  // Get user by ID
  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/get/${id}`);
  }
 // Get user by name
 getUserByName(name:string): Observable<User> {
  return this.http.get<User>(`${this.apiUrl}/getbyname/${name}`);
}
  // Update user
  updateUser(id: number, user: User): Observable<User> {
    return this.http.put<User>(`${this.apiUrl}/update/${id}`, user);
  }

  // Delete user
  deleteUser(id: number): Observable<string> {
    return this.http.delete<string>(`${this.apiUrl}/delete/${id}`);
  }
}
