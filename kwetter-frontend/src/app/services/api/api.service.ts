import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { catchError, tap, map } from 'rxjs/operators';
// import { Account } from '../../models/account';


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};
const apiUrl = 'http://localhost:8080/kwetter/api';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }
  
  handleError(error: HttpErrorResponse) {
    console.log(error);
    return throwError(error);
  }

  public getHttpClient() {
    return this.http;
  }

  public getApiUrl() {
    return apiUrl;
  }

  public getHttpOptions() {
    return httpOptions;
  }
}
