import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Group } from '../Entites/group';
import { Constants } from '../Components/Constants/Constants';
import { User } from '../Entites/user';

@Injectable({
  providedIn: 'root'
})
export class GroupService {
  groupId$:BehaviorSubject<number>=new BehaviorSubject<number>(0);

  private apiUrl = Constants.GROUP_API; // Update the port if needed

  constructor(private http: HttpClient) { }

  // Create a new group
  createGroup(group: Group): Observable<Group> {
    return this.http.post<Group>(`${this.apiUrl}/create`, group);
  }

  // Get groups by user ID
  getUserGroups(userId: number): Observable<Set<Group>> {
    return this.http.get<Set<Group>>(`${this.apiUrl}/getusergroups/${userId}`);
  }
  getGroupById(groupId: number): Observable<Group> {
    return this.http.get<Group>(`${this.apiUrl}/getgroup/${groupId}`);
  }
  // Get users in a specific group
  getUsersInGroup(groupId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/getuseringroup/${groupId}`);
  }

  // Update a group
  updateGroup(groupId: number, group: Group): Observable<Group> {
    return this.http.put<Group>(`${this.apiUrl}/update/${groupId}`, group);
  }

  // Delete a group
  deleteGroup(groupId: number): Observable<string> {
    return this.http.delete<string>(`${this.apiUrl}/delete/${groupId}`);
  }

  // Add a member to a group
  addMember(groupId: number, userName: string): Observable<Group> {
    return this.http.post<Group>(`${this.apiUrl}/addmember/${groupId}/${userName}`, {});
  }
 
  // Delete a member from a group
  deleteMember(groupId: number, userId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/deletemember/${groupId}/${userId}`);
  }
}
