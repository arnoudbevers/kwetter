import { Injectable } from '@angular/core';
import { ApiService } from '../api/api.service';
import { HttpClient } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RecaptchaService extends ApiService {

  constructor(http: HttpClient) {
    super(http);
  }

  validateRecaptcha(token: string) {
    return this.getHttpClient()
      .get(`${this.getApiUrl()}/auth/recaptcha/${token}`, this.getHttpOptions()).pipe(catchError(this.handleError));
  }
}
