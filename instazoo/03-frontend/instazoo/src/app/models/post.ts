import {UserName} from "./user-name";
import {ImageModel} from "./image-model";

export class Post {
  id?: number;
  title?: string;
  caption?: string;
  location?: string;
  images?: ImageModel [];
  likes?: number;
  comments?: Comment [];
  author?: UserName

  createdDate?: Date;

  likedByLoginUser?: boolean;
  loginUserAuthor?: boolean;
}
