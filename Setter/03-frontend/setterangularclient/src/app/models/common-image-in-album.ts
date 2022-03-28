import {CommonImage} from "./common-image";

export class CommonImageInAlbum extends CommonImage{
  nextImageId: number;
  previousImageId: number;
  likes?: number;

  likedByLoginUser: boolean;
  loginUserAuthor: boolean;
}
