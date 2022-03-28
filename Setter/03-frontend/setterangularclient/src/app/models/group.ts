import {CommonImage} from "./common-image";

export class Group {
  id: number;
  name?: string;
  description?: string;
  profilePhoto?: CommonImage;

  isLoginUserAdmin?: boolean;
  isLoginUserMember?: boolean;
  isLoginUserHasInvite?: boolean;
}
