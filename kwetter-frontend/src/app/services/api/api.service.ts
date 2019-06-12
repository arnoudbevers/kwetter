import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { catchError, tap, map } from 'rxjs/operators';


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};
const apiUrl = 'http://kwetter_backend:8080/kwetter/api';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }
  
  handleError(error: HttpErrorResponse) {
    console.error(error);
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
