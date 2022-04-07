import {UserName} from "./user-name";


export class Comment {
  id?: number;
  message?: string;
  author?: UserName

  createdDate?: Date;
  loginUserAuthor?: boolean;
}
