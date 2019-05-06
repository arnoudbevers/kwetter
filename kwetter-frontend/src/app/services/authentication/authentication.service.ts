import { Injectable } from '@angular/core';
import { ApiService } from '../api/api.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Credentials } from 'src/app/models/credentials';
import { StorageService } from '../storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService extends ApiService {

  constructor(http: HttpClient, private storageService: StorageService) {
    super(http);
  }

  isLoggedIn(): boolean {
    if (this.storageService.getItem('kwetter_jwt_token') == "" || this.storageService.getItem('kwetter_jwt_token') == null) {
      return false;
    }
    return true;
  }

  login(credentials: Credentials) {
    // TODO: Handle error
    return this.getHttpClient().post(`${this.getApiUrl()}/auth/login`, credentials, this.getHttpOptions());
  }
}
