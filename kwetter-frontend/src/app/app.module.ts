import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

// Modules
import { RoutingModule } from "./routing/routing.module";
import { HttpClientModule } from "@angular/common/http";

//Angular Material Modules
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";

// Components
import { AppComponent } from "./app.component";
import { LoginComponent } from "./components/login/login.component";
import { NavbarComponent } from "./components/navbar/navbar.component";

// Serivces
import { StorageService } from "./services/storage/storage.service";
import { TimelineComponent } from './components/timeline/timeline.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { SearchbarComponent } from './components/searchbar/searchbar.component';
import { HomepageProfileinfoComponent } from './components/homepage-profileinfo/homepage-profileinfo.component';

@NgModule({
  declarations: [AppComponent, LoginComponent, NavbarComponent, TimelineComponent, HomepageComponent, SearchbarComponent, HomepageProfileinfoComponent],
  imports: [
    BrowserModule,
    RoutingModule,
    BrowserAnimationsModule,
    NgbModule,
    HttpClientModule
  ],
  providers: [StorageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
