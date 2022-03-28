import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./auth/login/login.component";
import {RegisterComponent} from "./auth/register/register.component";
import {IndexComponent} from "./search/index/index.component";
import {AuthGuardService} from "./helper/auth-guard.service";
import {ProfileComponent} from "./user/profile/profile.component";
import {FriendListComponent} from "./friend/friend-list/friend-list.component";
import {GroupListComponent} from "./group/group-list/group-list.component";
import {VideoListComponent} from "./video/video-list/video-list.component";
import {AudioListComponent} from "./audio/audio-list/audio-list.component";
import {PhotoListComponent} from "./photo/photo-list/photo-list.component";
import {FeedListComponent} from "./feed/feed-list/feed-list.component";
import {SettingsComponent} from "./user-settings/settings/settings.component";
import {ChannelListComponent} from "./message/channel-list/channel-list.component";
import {GroupProfileComponent} from "./group/group-profile/group-profile.component";
import {SinglePhotoComponent} from "./photo/single-photo/single-photo.component";
import {ChannelComponent} from "./message/channel/channel.component";
import {SinglePostComponent} from "./common/single-post/single-post.component";
import {SingleVideoComponent} from "./video/single-video/single-video.component";

import {CreateNewPasswordComponent} from "./auth/create-new-password/create-new-password.component";
import {ActivationUserComponent} from "./auth/activation-user/activation-user.component";
import {ForgotPasswordComponent} from "./auth/forgot-password/forgot-password.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},

  {path: 'forgotPassword', component: ForgotPasswordComponent},
  {path: 'createNewPassword/:newPasswordCode', component: CreateNewPasswordComponent},
  {path: 'activationUser/:activationCode', component: ActivationUserComponent},

  {path: 'search', component: IndexComponent, canActivate: [AuthGuardService]},
  {path: 'users/:id', component: ProfileComponent, canActivate: [AuthGuardService]},
  {path: 'feed', component: FeedListComponent, canActivate: [AuthGuardService]},

  {path: 'channels', component: ChannelListComponent, canActivate: [AuthGuardService]},
  {path: 'channels/:id', component: ChannelComponent, canActivate: [AuthGuardService]},

  {path: 'friends', component: FriendListComponent, canActivate: [AuthGuardService]},

  {path: 'groups', component: GroupListComponent, canActivate: [AuthGuardService]},

  {path: ':hostCategory/:id/posts/:postId', component: SinglePostComponent, canActivate: [AuthGuardService]},

  {path: ':hostCategory/:id/photos', component: PhotoListComponent, canActivate: [AuthGuardService]},
  {path: ':hostCategory/:id/photos/:photoId', component: SinglePhotoComponent, canActivate: [AuthGuardService]},

  //{path: 'groups/:id/photos', component: PhotoListComponent, canActivate: [AuthGuardService]},
  //{path: 'groups/:id/photos/:photoId', component: SinglePhotoComponent, canActivate: [AuthGuardService]},

  {path: ':hostCategory/:id/audios', component: AudioListComponent, canActivate: [AuthGuardService]},

  {path: ':hostCategory/:id/videos', component: VideoListComponent, canActivate: [AuthGuardService]},
  {path: ':hostCategory/:id/videos/:videoId', component: SingleVideoComponent, canActivate: [AuthGuardService]},

  {path: 'settings', component: SettingsComponent, canActivate: [AuthGuardService]},

  {path: 'groups/:id', component: GroupProfileComponent, canActivate: [AuthGuardService]},

  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: '**', redirectTo: '/login', pathMatch: 'full'},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
