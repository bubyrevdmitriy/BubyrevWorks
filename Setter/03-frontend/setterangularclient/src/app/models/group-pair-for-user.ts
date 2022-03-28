import {GroupAuthor} from "./group-author";
import {UserAuthor} from "./user-author";

export class GroupPairForUser {
  id: number;
  userId: number;
  myGroup: GroupAuthor;
  isUserAdmin: boolean;
}
