import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
  })

export class UserService {

    private apiUrl = "http://localhost:8080";

  constructor(private httpClient: HttpClient) { }

  loginUser(username: String, password: String): Observable<any> {
    const user : any = {
        "userName": username,
        "password": password
    };
    return this.httpClient.post<any>(this.apiUrl+"/api/login", user);
  }

}  