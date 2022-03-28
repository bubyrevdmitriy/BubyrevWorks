import {UserAuthor} from "./user-author";
import {GroupAuthor} from "./group-author";

export class AudioFile {

  likes?: number;
  userAuthor?: UserAuthor;
  userGroupAuthor: GroupAuthor;

  isLoaded: boolean;
  isStopped: boolean;

  comments?: Comment[];
  createdDate?: Date;

  likedByLoginUser: boolean;
  loginUserAuthor: boolean;

  nextVideoFileId: number;
  previousVideoFileId: number;

  constructor(public id: number,
              public description: string,
              public contentType: string,
              public previewUrl: string,
              public streamUrl: string) {
  };

}
