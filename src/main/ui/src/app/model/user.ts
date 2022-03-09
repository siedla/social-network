import { Post } from "./post";

export interface User {
    id: Number;
    firstName: string;
    lastName: string;
    email: string;
    photoUrl: string;
    posts: Post[];
}