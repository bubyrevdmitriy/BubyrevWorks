import {ImageModel} from "./image-model";


export class User {
  id: number;
  email: string;
  username: string;
  firstname: string;
  lastname: string;
  bio: string;

  isLoginUserPage: boolean;
  profileImage: ImageModel;
}
