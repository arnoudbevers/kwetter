import { Injectable } from '@angular/core';
import { ApiService } from '../api/api.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Credentials } from 'src/app/models/credentials';
import { tap } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService extends ApiService {

  constructor(http: HttpClient) {
    super(http);
  }

  isLoggedIn(): boolean {
    if (localStorage.getItem('kwetter_jwt_token') == "" || localStorage.getItem('kwetter_jwt_token') == null) {
      return false;
    }
    return true;
  }

  login(credentials: Credentials) {
    // TODO: Handle error
    return this.getHttpClient().post(`${this.getApiUrl()}/auth/login`, credentials, this.getHttpOptions());
  }
}
