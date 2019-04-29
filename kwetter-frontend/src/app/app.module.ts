import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

// Modules
import { RoutingModule } from "./routing/routing.module";

//Angular Material Modules
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

// Components
import { AppComponent } from "./app.component";
import { LoginComponent } from "./components/login/login.component";
import { NavbarComponent } from "./components/navbar/navbar.component";

// Serivces
import { StorageService } from "./services/storage/storage.service";

@NgModule({
  declarations: [AppComponent, LoginComponent, NavbarComponent],
  imports: [
    BrowserModule, 
    RoutingModule, 
    BrowserAnimationsModule,
    NgbModule
  ],
  providers: [StorageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
