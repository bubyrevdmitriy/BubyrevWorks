import {UserAuthor} from "./user-author";
import {CommonImage} from "./common-image";
import {GroupAuthor} from "./group-author";

export class Post {
  id?: number;
  caption?: string;
  commonImages?: CommonImage[];
  likes?: number;
  userAuthor?: UserAuthor;
  userGroupAuthor: GroupAuthor;
  comments?: Comment[];
  createdDate?: Date;

  likedByLoginUser: boolean;
  loginUserAuthor: boolean;

  nextPostId: number;
  previousPostId: number;
}
