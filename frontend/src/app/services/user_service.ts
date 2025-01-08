import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { environment } from '../environment';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private readonly baseUrl = environment["endPoint"];

  constructor(private http: HttpClient) {}

  getItems(page: number, size: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/usuarios?page=${page}&size=${size}&sort=id,desc`);
  }

  createItem(item: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/usuarios`, item);
  }  

  deleteItem(id: any): Observable<any> {
    return this.http.delete(`${this.baseUrl}/usuarios/${id}`);
  }

  updateItem(item: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/usuarios`, item);
  }
}
