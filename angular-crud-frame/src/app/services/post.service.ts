import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post } from '../models/model-data';

@Injectable({
    providedIn: 'root'
  })

export class PostService {

    private apiUrl = "http://localhost:8080";

  constructor(private httpClient: HttpClient) { }

  getPosts(postRequestParam: any, page: number, pageSize: number): Observable<any> {

    page = page? page : 0;
    pageSize = pageSize? pageSize : 10;

    let params = new HttpParams()
    .set('page', page.toString())
    .set('size', pageSize.toString());
    if(postRequestParam['title'])params.set('title', postRequestParam['title']);
    console.log(params);
    return this.httpClient.get<any>(this.apiUrl+"/api/auth/post/getall", { params: params });
  }

  savePost(post: Post): Observable<Post> {
    if(!post.id)
    return this.httpClient.post<Post>(this.apiUrl+"/api/auth/post/register", post);
    else return this.httpClient.put<Post>(this.apiUrl+"/api/auth/post/update", post);
  }

  getPostById(id: number): Observable<Post> {
    const url = this.apiUrl+"/api/auth/post/get-by-id";
    return this.httpClient.get<Post>(`${url}/${id}`);
  }
}  