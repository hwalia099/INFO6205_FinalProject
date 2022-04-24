import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { PossiblePlay } from './PossiblePlay';
import { RequestObject } from './RequestObject';

@Injectable({
  providedIn: 'root'
})
export class MenaceService {

  baseUrl:string = "http://localhost:8080/";

  constructor(private http: HttpClient) { }

  getComputerMove(board:RequestObject[]): Observable<PossiblePlay> {
    const headers = { 'content-type': 'application/json',
    'Access-Control-Allow-Origin': '*'};
  
    const body=JSON.stringify(board);
    console.log(body)
    return this.http.post<PossiblePlay>(this.baseUrl + 'process', body,{'headers':headers});
  }

}
