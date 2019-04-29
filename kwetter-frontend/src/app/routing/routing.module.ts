import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";

// Components
import { LoginComponent } from "../components/login/login.component";

const appRoutes: Routes = [
  // { path: "", redirectTo: "account/login", pathMatch: 'full' },
  { path: "account/login", component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes), NgbModule],
  exports: [RouterModule]
})
export class RoutingModule {}
