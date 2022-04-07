import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MaterialModule} from "./material-module";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AppRoutingModule} from "./app-routing-module";
import {authInterceptorProviders} from "./helper/auth-interceptor.service";
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { PasswordRestoreComponent } from './auth/password-restore/password-restore.component';
import { ProfileComponent } from './user/profile/profile.component';
import { AddPostComponent } from './common/add-post/add-post.component';
import { EditUserComponent } from './user/edit-user/edit-user.component';
import { UserPostsComponent } from './user/user-posts/user-posts.component';
import { IndexComponent } from './layout/index/index.component';
import { NavigationComponent } from './layout/navigation/navigation.component';
import { PostElementComponent } from './common/post-element/post-element.component';
import { CommentElementComponent } from './common/comment-element/comment-element.component';
import {authErrorInterceptorProviders} from "./helper/error-interceptor.service";
import { SetterImagePipePipe } from './service/setter-image-pipe.pipe';
import { UserNameComponent } from './common/user-name/user-name.component';
import { AddCommentComponent } from './common/add-comment/add-comment.component';
import { UserLikedListMatDialogComponent } from './common/user-liked-list-mat-dialog/user-liked-list-mat-dialog.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    PasswordRestoreComponent,
    ProfileComponent,
    AddPostComponent,
    EditUserComponent,
    UserPostsComponent,
    IndexComponent,
    NavigationComponent,
    PostElementComponent,
    CommentElementComponent,
    SetterImagePipePipe,
    UserNameComponent,
    AddCommentComponent,
    UserLikedListMatDialogComponent
  ],
  imports: [
    BrowserModule,
    NgbModule,
    BrowserAnimationsModule,
    MaterialModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule
  ],
  providers: [authInterceptorProviders, authErrorInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
