import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class TokenService {
  private readonly baseUrl = environment["endPoint"];
    
  constructor(private http: HttpClient) {}
  
  getToken(): string | null {
    if (typeof window !== 'undefined' && localStorage) {
      return localStorage.getItem('jwtToken');
    }
    return null;
  }

  setToken(token: string): void {
    if (typeof window !== 'undefined' && localStorage) {
      localStorage.setItem('jwtToken', token);
    }
  }

  getJwtToken(): Observable<any> {
    const tokenUrl = `${this.baseUrl}/public`;
    return this.http.get(tokenUrl);
  }
}