import { Component, Input, OnInit} from '@angular/core';
import { Conversation } from 'src/app/model/conversation';
import { User } from 'src/app/model/user';
import { Message } from 'src/app/model/message';
import { ConversationService } from 'src/app/services/conversation.service';
import { UserService } from 'src/app/services/user.service';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { DataService } from 'src/app/services/data.service';
import { Subscription } from 'rxjs';
import { environment } from 'src/environments/environment';
@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit{

  constructor(private userService: UserService, private conversationService: ConversationService, private dataService: DataService) {

  }

  user: User = {} as User;

  type: string = "text";
  chatWithUserId: Number = -1;
  chattingUser: User = {} as User;

  notifierSubscription: Subscription = this.dataService.chatWithUser.subscribe(data => {
    this.chatWithUserId = data;
    this.userService.getUserById(data).subscribe( t=>this.chattingUser = t);
    this.userService.getCurrentUser().subscribe(data => {this.user = data;
      this.conversationService.getConversation(this.user.id, this.chatWithUserId).subscribe(data => {this.conversation = data});});
});

  ngOnInit(): void {
  
    this.connect();
    
  }

  conversation: Conversation = {} as Conversation;


  getSender(id:Number) {
    for(let i=0; i<this.conversation.users.length; i++) {
      if(id == this.conversation.users[i].id){
        return this.conversation.users[i].firstName+" "+this.conversation.users[i].lastName;
      }
    }
    return "";
  }
  getSenderPhoto(id:Number) {
    for(let i=0; i<this.conversation.users.length; i++) {
      if(id == this.conversation.users[i].id){
        return this.conversation.users[i].photoUrl;
      }
    }
    return "";
  }

  disabled = true;
  newmessage: Message = {} as Message;
  private stompClient: any;
  connect() {
    const socket = new SockJS(environment.baseUrl+'conversation');
    this.stompClient = Stomp.over(socket);
    this.stompClient.debug = null
    const _this = this;
    this.stompClient.connect({}, function (frame: any) {
      _this.stompClient.subscribe('/conversation/message', function(hello: any){
        _this.showMessage(JSON.parse(hello.body));
      });
   });
  }
  sendMessage(event: any) {
    let message: Message = {} as Message;
    message.fromId = this.user.id;
    message.postDate = new Date();
    message.text = event.message;
    this.stompClient.send(
      '/current/add/'+this.conversation.id,
      {},
     JSON.stringify( message),
    );
  }
  showMessage(message: Message) {
    this.conversation.messages.push(message);
  }
  setConnected(connected: boolean) {
    this.disabled = !connected;
  }
  closeChat(){
    if(this.chatWithUserId >= 0){
      this.chatWithUserId = -1;
    }
  }
}
