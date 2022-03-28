import {UserAuthor} from "./user-author";

export class Channel {
  id?: number;
  senderId?: number;
  recipientId?: number;
  lastMessage?: string;
  senderUser?: UserAuthor;
  recipientUser?: UserAuthor;
}
