import { Post } from "./post";

export interface User {
    id: Number;
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    photoUrl: string;
    posts: Post[];
    friends: User[];
}