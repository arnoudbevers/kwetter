export class Friendship {
  public following: string;
  public followed: string;

  constructor(following: string, followed: string) {
    this.following = following;
    this.followed = followed;
  }
}