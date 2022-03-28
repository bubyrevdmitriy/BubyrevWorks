import {GroupAuthor} from "./group-author";
import {UserAuthor} from "./user-author";

export class GroupPairForUserGroup {
  id: number;
  userGroupId: number;
  user: UserAuthor;
  isUserAdmin: boolean;
}
