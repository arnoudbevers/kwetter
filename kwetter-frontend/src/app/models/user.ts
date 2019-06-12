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
    password: string;

    constructor(username: string, email: string, location: string, websiteUrl: string, bio: string, password: string) {
      this.username = username;
      this.email = email;
      this.location = location;
      this.websiteUrl = websiteUrl;
      this.bio = bio;
      this.password = password;
    }
}