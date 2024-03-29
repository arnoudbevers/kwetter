import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";

// Components
import { LoginComponent } from "../components/login/login.component";
import { HomepageComponent } from '../components/homepage/homepage.component';
import { RegisterComponent } from '../components/register/register.component';
import { ProfileComponent } from '../components/profile/profile.component';
import { VerifyComponent } from '../components/verify/verify.component';

const appRoutes: Routes = [
  { path: "", component: HomepageComponent },
  { path: "verify/:uuid", component: VerifyComponent },
  { path: "account/login", component: LoginComponent },
  { path: "account/register", component: RegisterComponent },
  { path: "user/:username", component: ProfileComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes), NgbModule],
  exports: [RouterModule]
})
export class RoutingModule { }
