import { User } from "./user";

export interface Post {
    id: Number;
    description: string;
    postDate: string;
    postTime: string;
    likes: number;
    user: User;
}