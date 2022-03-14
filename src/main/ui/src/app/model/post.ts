import { User } from "./user";

export interface Post {
    id: Number;
    description: string;
    postDate: string;
    likes: number;
    user: User;
    photoUrl: string;
}