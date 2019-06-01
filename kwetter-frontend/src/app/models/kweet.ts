import { User } from './user';
import { TouchSequence } from 'selenium-webdriver';

export class Kweet {
    private message: string;
    private sent: number;
    private sender: User;

    constructor(message: string, sent: number, sender: User) {
      this.message = message;
      this.sent = sent;
      this.sender = sender;
    }
}