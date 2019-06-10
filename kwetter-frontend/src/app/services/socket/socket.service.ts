import { Injectable } from "@angular/core";
import { Subject } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class SocketService {
  open(url: string) {
    let states = new Subject();
    let messages = new Subject();

    let socket = new WebSocket(url);

    socket.addEventListener("open", () => states.next("open"));
    socket.addEventListener("close", () => states.next("closed"));
    socket.addEventListener("message", e => {
      messages.next(e.data);
    });

    return {
      states,
      messages,
      send(message: string) {
        socket.send(message);
      }
    };
  }
}
