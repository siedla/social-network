import { Message } from "./message";
import { User } from "./user";


export interface Conversation {
    id: Number;
    messages: Message[];
    users: User[];
}