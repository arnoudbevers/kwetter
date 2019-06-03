import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

// Modules
import { RoutingModule } from "./routing/routing.module";
import { HttpClientModule } from "@angular/common/http";
import { JwtModule } from "@auth0/angular-jwt";
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; // <== add the imports!


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
import { ProfileComponent } from './components/profile/profile.component';
import { ProfileProfilecardComponent } from './components/profile-profilecard/profile-profilecard.component';
import { ProfileKweetsComponent } from './components/profile-kweets/profile-kweets.component';
import { DatePipe } from './pipes/date.pipe';
import { ProfileDetailsComponent } from './components/profile-details/profile-details.component';

@NgModule({
  declarations: [AppComponent, LoginComponent, NavbarComponent, HomepageComponent, HomepageProfileinfoComponent, HomepageKweetboxComponent, HomepageTimelineComponent, RegisterComponent, ProfileComponent, ProfileProfilecardComponent, ProfileKweetsComponent, DatePipe, ProfileDetailsComponent],
  imports: [
    BrowserModule,
    RoutingModule,
    BrowserAnimationsModule,
    NgbModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: function tokenGetter() {
          return localStorage.getItem("kwetter_jwt_token");
        }, 
        whitelistedDomains: ["localhost:8080"],
        blacklistedRoutes: ["localhost:8080/kwetter/api/auth/login"]
      }
    })
  ],
  providers: [StorageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
