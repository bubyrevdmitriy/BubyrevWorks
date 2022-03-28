import {GroupAuthor} from "./group-author";

export class GroupPair {
  id: number;
  userId: number;
  userGroupId: number;
  myGroup: GroupAuthor;
  isUserAdmin: Boolean;
}
