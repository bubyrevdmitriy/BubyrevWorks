import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {LoginComponent} from './auth/login/login.component';
import {RegisterComponent} from './auth/register/register.component';
import {IndexComponent} from './search/index/index.component';
import {NavigationComponent} from './common/navigation/navigation.component';
import {ProfileComponent} from './user/profile/profile.component';
import {UserPostsComponent} from './user/user-posts/user-posts.component';
import {EditPostComponent} from './user/edit-post/edit-post.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AppRoutingModule} from "./app-routing.module";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {MaterialModule} from "./material-module";
import {authInterceptorProviders, AuthInterceptorService} from "./helper/auth-interceptor.service";
import {authErrorInterceptorProviders} from "./helper/error-interceptor.service";
import { SideMenuComponent } from './common/side-menu/side-menu.component';
import { SideMenuSmallComponent } from './common/side-menu-small/side-menu-small.component';
import {MatExpansionModule} from "@angular/material/expansion";
import { FriendListComponent } from './friend/friend-list/friend-list.component';
import { GroupListComponent } from './group/group-list/group-list.component';
import { PhotoListComponent } from './photo/photo-list/photo-list.component';
import { VideoListComponent } from './video/video-list/video-list.component';
import { AudioListComponent } from './audio/audio-list/audio-list.component';
import { FeedListComponent } from './feed/feed-list/feed-list.component';
import { SettingsComponent } from './user-settings/settings/settings.component';
import { ChannelListComponent } from './message/channel-list/channel-list.component';
import { AccessSettingsComponent } from './user-settings/access-settings/access-settings.component';
import { UpdateUserComponent } from './user-settings/update-user/update-user.component';
import { SearchUserListComponent } from './search/search-user-list/search-user-list.component';
import { SearchPostListComponent } from './search/search-post-list/search-post-list.component';
import { SearchGroupListComponent } from './search/search-group-list/search-group-list.component';
import { SearchVideoListComponent } from './search/search-video-list/search-video-list.component';
import { SearchAudioListComponent } from './search/search-audio-list/search-audio-list.component';
import {SafeHtml} from "./service/safe-html";
import {SetterDatePipe} from "./service/setter-date-pipe";
import { CreateGroupComponent } from './group/create-group/create-group.component';
import { GroupProfileComponent } from './group/group-profile/group-profile.component';
import { GroupInviteSendComponent } from './group/group-invite-send/group-invite-send.component';
import { GroupInviteReceivedComponent } from './group/group-invite-received/group-invite-received.component';
import { GroupPairsComponent } from './group/group-pairs/group-pairs.component';
import { EditGroupComponent } from './group/edit-group/edit-group.component';
import { GroupInviteComponent } from './user/group-invite/group-invite.component';
import { SinglePhotoComponent } from './photo/single-photo/single-photo.component';
import { UserPageGroupListComponent } from './user/user-page-group-list/user-page-group-list.component';
import { UserPageFriendListComponent } from './user/user-page-friend-list/user-page-friend-list.component';
import { PostElementComponent } from './common/post-element/post-element.component';
import { UserSmallElementComponent } from './common/user-small-element/user-small-element.component';
import { GroupSmallElementComponent } from './group/group-small-element/group-small-element.component';
import {SetterImagePipe} from "./service/setter-image.pipe";
import { GroupPageUsersListComponent } from './group/group-page-users-list/group-page-users-list.component';
import { GroupPageAdminsListComponent } from './group/group-page-admins-list/group-page-admins-list.component';
import { ChannelComponent } from './message/channel/channel.component';
import {SetterDateOnlyHHMMPipe} from "./service/setter-date-only-hhmmpipe";
import {ScrollingModule} from "@angular/cdk/scrolling";
import { ImageInAlbumSmallElementComponent } from './photo/image-in-album-small-element/image-in-album-small-element.component';
import { UserLikedListMatDialogComponent } from './common/user-liked-list-mat-dialog/user-liked-list-mat-dialog.component';
import { SinglePostComponent } from './common/single-post/single-post.component';
import { AddPostComponent } from './common/add-post/add-post.component';
import { AddCommentComponent } from './common/add-comment/add-comment.component';
import { CommentElementComponent } from './common/comment-element/comment-element.component';
import { AudioUploadComponent } from './audio/audio-upload/audio-upload.component';
import { VideoUploadComponent } from './video/video-upload/video-upload.component';
import { VideoPlayerComponent } from './video/video-player/video-player.component';
import { VideoPreviewComponent } from './video/video-preview/video-preview.component';
import { SingleVideoComponent } from './video/single-video/single-video.component';
import {SecurePipeImgService} from "./service/secure-pipe-img.service";
import {SecurePipeAudioService} from "./service/secure-pipe-audio.service";
import {SecurePipeVideoService} from "./service/secure-pipe-video.service";
import {CookieService} from "ngx-cookie-service";
import { SingleAudioComponent } from './audio/single-audio/single-audio.component';
import { ActivationUserComponent } from './auth/activation-user/activation-user.component';
import { CreateNewPasswordComponent } from './auth/create-new-password/create-new-password.component';
import { ForgotPasswordComponent } from './auth/forgot-password/forgot-password.component';
import { MyFriendsComponent } from './friend/my-friends/my-friends.component';
import { FriendInvitesSendComponent } from './friend/friend-invites-send/friend-invites-send.component';
import { FriendInvitesReceivedComponent } from './friend/friend-invites-received/friend-invites-received.component';
import { GroupInviteSmallElementComponent } from './group/group-invite-small-element/group-invite-small-element.component';
import { ChannelElementComponent } from './message/channel-element/channel-element.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    IndexComponent,
    NavigationComponent,
    ProfileComponent,
    UserPostsComponent,
    EditPostComponent,
    SideMenuComponent,
    SideMenuSmallComponent,
    FriendListComponent,
    GroupListComponent,
    PhotoListComponent,
    VideoListComponent,
    AudioListComponent,
    FeedListComponent,
    SettingsComponent,
    ChannelListComponent,
    AccessSettingsComponent,
    UpdateUserComponent,
    SearchUserListComponent,
    SearchPostListComponent,
    SearchGroupListComponent,
    SearchVideoListComponent,
    SearchAudioListComponent,
    SafeHtml,
    SecurePipeImgService,
    SecurePipeVideoService,
    SecurePipeAudioService,
    SetterDatePipe,
    SetterDateOnlyHHMMPipe,
    SetterImagePipe,
    CreateGroupComponent,
    GroupProfileComponent,
    GroupInviteSendComponent,
    GroupInviteReceivedComponent,
    GroupPairsComponent,
    EditGroupComponent,
    GroupInviteComponent,
    SinglePhotoComponent,
    SingleVideoComponent,
    UserPageGroupListComponent,
    UserPageFriendListComponent,
    PostElementComponent,
    UserSmallElementComponent,
    GroupSmallElementComponent,
    GroupPageUsersListComponent,
    GroupPageAdminsListComponent,
    ChannelComponent,
    ImageInAlbumSmallElementComponent,
    UserLikedListMatDialogComponent,
    SinglePostComponent,
    AddPostComponent,
    AddCommentComponent,
    CommentElementComponent,
    AudioUploadComponent,
    VideoUploadComponent,
    VideoPlayerComponent,
    VideoPreviewComponent,
    SingleVideoComponent,
    SingleAudioComponent,
    ActivationUserComponent,
    CreateNewPasswordComponent,
    ForgotPasswordComponent,
    MyFriendsComponent,
    FriendInvitesSendComponent,
    FriendInvitesReceivedComponent,
    GroupInviteSmallElementComponent,
    ChannelElementComponent
  ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        ReactiveFormsModule,
        AppRoutingModule,
        NgbModule,
        MaterialModule,
        HttpClientModule,
        FormsModule,
        MatExpansionModule,
        ScrollingModule
    ],
  providers: [
    CookieService,
    authInterceptorProviders,
    authErrorInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
