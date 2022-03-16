import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Message } from '../model/message';

@Injectable({
  providedIn: 'root'
})
export class ConversationService {

  private url = 'http://localhost:8081/conversations';

  constructor(private http: HttpClient) {

  }

  public getConversation(firstUserId: Number, secondUserId: Number): Observable<any> {
    return this.http.get<any>(this.url+"/"+firstUserId+"/"+secondUserId);
  }

  public sendMessage(message: Message, conversationId: Number) {
    return this.http.post<any>(this.url+"/message/"+conversationId, message);
  }
}

