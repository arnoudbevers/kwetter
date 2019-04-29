import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

// Modules
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { RoutingModule } from "./routing/routing.module";

// Components
import { AppComponent } from "./app.component";
import { LoginComponent } from "./components/login/login.component";
import { NavbarComponent } from "./components/navbar/navbar.component";

// Serivces
import { StorageService } from "./services/storage/storage.service";

@NgModule({
  declarations: [AppComponent, LoginComponent, NavbarComponent],
  imports: [BrowserModule, RoutingModule, NgbModule],
  providers: [StorageService],
  bootstrap: [AppComponent]
})
export class AppModule {}
