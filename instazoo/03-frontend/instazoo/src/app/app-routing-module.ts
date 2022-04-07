import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {PasswordRestoreComponent} from "./auth/password-restore/password-restore.component";
import {RegisterComponent} from "./auth/register/register.component";
import {LoginComponent} from "./auth/login/login.component";
import {IndexComponent} from "./layout/index/index.component";
import {AuthGuardService} from "./helper/auth-guard.service";
import {ProfileComponent} from "./user/profile/profile.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'forgotPassword', component: PasswordRestoreComponent},
  {path: 'main', component: IndexComponent, canActivate: [AuthGuardService]},
  {path: 'user/:id', component: ProfileComponent, canActivate: [AuthGuardService]},
  {path: '', redirectTo: 'login', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
