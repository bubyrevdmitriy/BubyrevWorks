import {CommonImage} from "./common-image";

export class User {
  id?: number;
  email?: string;
  profilePhoto?: CommonImage;
  firstName?: string;
  lastName?: string;
  middleName?: string;
  phone?: string;
  bio?: string;
  bornDate?;
  isLoginUserPage?: boolean;
  isLoginUserFriend?: boolean;
  sendToLoginUserFriendInvite?: boolean;
  receiveFromLoginUserFriendInvite?: boolean;
  channelIdWithLoginUserPage?: number;
}
