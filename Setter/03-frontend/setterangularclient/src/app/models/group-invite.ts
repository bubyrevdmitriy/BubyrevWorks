import {UserAuthor} from "./user-author";
import {GroupAuthor} from "./group-author";

export class GroupInvite {
  id: number;
  futureGroup: GroupAuthor;
  otherUser: UserAuthor;
}
