import { Kweet } from './kweet';

export class User {
    id: number;
    uuid: String;
    username: String;
    email: String;
    picture: String;
    location: String;
    websiteUrl: String;
    bio: String;
    kweets: Kweet[];
    following: User[];
    followers: User[];
}