import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Constants } from '../Components/Constants/Constants';

@Injectable({
  providedIn: 'root'
})
export class EventService {
  private baseUrl = Constants.EVENT_API; 

  constructor(private http: HttpClient) {}

  getEventsByUser(userId: number): Observable<Event[]> {
    return this.http.get<Event[]>(`${this.baseUrl}/geteventsbyuser/${userId}`);
  }

  getEventsByGroup(groupId: number): Observable<Event[]> {
    return this.http.get<Event[]>(`${this.baseUrl}/geteventsbygroup/${groupId}`);
  }

  addEvent(event: Event): Observable<Event> {
    return this.http.post<Event>(`${this.baseUrl}/addevent`, event);
  }

  deleteEvent(eventId: number): Observable<string> {
    return this.http.delete<string>(`${this.baseUrl}/delete/${eventId}`);
  }

  updateEvent(eventId: number, event: Event): Observable<Event> {
    return this.http.put<Event>(`${this.baseUrl}/update/${eventId}`, event);
  }

  getEventMembers(eventId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/geteventmembers/${eventId}`);
  }

  addMember(eventId: number, userName: string): Observable<Event> {
    return this.http.post<Event>(`${this.baseUrl}/addmember/${eventId}/${userName}`, {});
  }
}
