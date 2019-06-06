import { Kweet } from './kweet';

export class User {
    id: number;
    uuid: string;
    username: string;
    email: string;
    picture: string;
    location: string;
    websiteUrl: string;
    bio: string;
    kweets: Kweet[];
    following: User[];
    followers: User[];
}