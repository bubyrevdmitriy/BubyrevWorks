import {UserAuthor} from "./user-author";

export class Message {
  id?: number;
  senderId?: number;
  recipientId?: number;

  content?: string;

  createdDate: Date;
  channelId: number;
  status: string;
}
