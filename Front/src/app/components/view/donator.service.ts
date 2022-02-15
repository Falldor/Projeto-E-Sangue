import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Donator } from './donator.model';
import { Sangue } from './sangue.model';

@Injectable({
  providedIn: 'root'
})
export class DonatorService {

  baseUrl: String = environment.baseUrl;

  constructor(
    private http: HttpClient,
    private _snack: MatSnackBar,
  ) { }

  findAll():Observable<Donator[]> {
    const url = `${this.baseUrl}doadores`
    console.log(url);
    return this.http.get<Donator[]>(url);
  }

  createDonator(donator: Donator): Observable<Donator>{
    const url = `${this.baseUrl}doadores`
    console.log(donator);
    console.log(url);
    return this.http.post<Donator>(url, donator);
  }

  findById(id: String):Observable<Donator> {
    const url = `${this.baseUrl}doadores/${id}`
    return this.http.get<Donator>(url)
  }

  delete(id: String): Observable<void> {
    const url = `${this.baseUrl}doadores/${id}`
    return this.http.delete<void>(url)
  }

  update(id: String, donator: Donator): Observable<void> {
    const url = `${this.baseUrl}doadores/${id}`
    return this.http.put<void>(url, donator)
  }

  mensagem(str: String): void {
    this._snack.open(`${str}`, 'OK', {
      horizontalPosition: 'end',
      verticalPosition: 'top',
      duration: 3000
    });
  }
  buscaSangue(str:String):Observable<number>{
    const url=`${this.baseUrl}doadores/qtd/${str}`
    return this.http.get<number>(url)
  }

  MenorDoador():Observable<String>{
    const url=`${this.baseUrl}doadores/qtd/`
    console.log(url)
    return this.http.get<String>(url);
  }
}
