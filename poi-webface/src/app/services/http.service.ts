import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

export interface RequestOptions {
  headers?: HttpHeaders,
  params?: HttpParams,
  withCredentials?: boolean,
  responseType?: 'json'
}

@Injectable()
export class HttpService {

  constructor(private http: HttpClient) {
  }

  get<T>(url: string, options: RequestOptions = this.getDefaultOptions()): Observable<T> {
    return this.http.get<T>(url, options);
  }

  post<T>(url: string, body: any = null, options: RequestOptions = this.getDefaultOptions()): Observable<T> {
    return this.http.post<T>(url, body, options);
  }

  put<T>(url: string, body: any = null, options: RequestOptions = this.getDefaultOptions()): Observable<T> {
    return this.http.put<T>(url, body, options);
  }

  delete<T>(url: string, options: RequestOptions = this.getDefaultOptions()): Observable<T> {
    return this.http.delete<T>(url, options);
  }

  getDefaultOptions() : RequestOptions {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json;charset=utf-8'
      }),
      params: null,
      withCredentials: false,
      responseType: 'json'
    }
  }
}
