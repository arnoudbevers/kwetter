import { Kweet } from './kweet';

export class User {
    private id: number;
    private uuid: String;
    private username: String;
    private email: String;
    private picture: String;
    private location: String;
    private websiteUrl: String;
    private bio: String;
    private kweets: Kweet[];
    private following: User[];
    private followers: User[];
}