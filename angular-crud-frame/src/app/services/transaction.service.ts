import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post, Transaction } from '../models/model-data';

@Injectable({
    providedIn: 'root'
  })

export class TransactionService {

    private apiUrl = "http://localhost:8080";

  constructor(private httpClient: HttpClient) { }

  getTransactions(postRequestParam: any, page: number, pageSize: number): Observable<any> {

    page = page ? page : 0;
    pageSize = pageSize ? pageSize : 10;

    // Prepare the payload for the POST request
    const payload = {
        customerId: postRequestParam['customerId'] || null,
        accountNumber: postRequestParam['accountNumber'] || null,
        description: postRequestParam['description'] || null,
        page: page + 1,
        size: pageSize
    };

    // Send a POST request
    return this.httpClient.post<any>(this.apiUrl + "/api/auth/transaction/get-all", payload);
  }

  saveTransaction(transaction: Transaction): Observable<Transaction> {
    return this.httpClient.post<Transaction>(this.apiUrl+"/api/auth/transaction/update", transaction);
  }

  getTransactionById(id: number): Observable<any> {
    const url = this.apiUrl+"/api/auth/transaction/getbyId";
    return this.httpClient.get<any>(`${url}/${id}`);
  }

  uploadFile(formData: FormData): Observable<any> {
    const url = this.apiUrl+"/api/auth/transaction/process";
    return this.httpClient.post(url, formData);
  }
}  