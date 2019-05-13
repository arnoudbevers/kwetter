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
import { HomepageComponent } from './components/homepage/homepage.component';
import { HomepageProfileinfoComponent } from './components/homepage-profileinfo/homepage-profileinfo.component';
import { HomepageKweetboxComponent } from './components/homepage-kweetbox/homepage-kweetbox.component';
import { HomepageTimelineComponent } from './components/homepage-timeline/homepage-timeline.component';
import { RegisterComponent } from './components/register/register.component';

@NgModule({
  declarations: [AppComponent, LoginComponent, NavbarComponent, HomepageComponent, HomepageProfileinfoComponent, HomepageKweetboxComponent, HomepageTimelineComponent, RegisterComponent],
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
