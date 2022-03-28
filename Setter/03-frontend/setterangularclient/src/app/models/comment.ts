import {UserAuthor} from "./user-author";

export class Comment {
  id?: number;
  text?: string;
  likes?: number;
  userAuthor?: UserAuthor;
  createdDate?: Date;
  postId?: number;

  likedByLoginUser: boolean;
  loginUserAuthor: boolean;
}
