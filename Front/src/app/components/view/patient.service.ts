import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Patient } from './patient.model';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  baseUrl: String = environment.baseUrl;

  constructor(
    private http: HttpClient,
    private _snack: MatSnackBar
  ) { }

  findAll():Observable<Patient[]> {
    const url = `${this.baseUrl}pacientes`
    return this.http.get<Patient[]>(url);
  }

  createPatient(patient: Patient): Observable<Patient>{
    const url = `${this.baseUrl}pacientes`;
    console.log(patient);
    console.log(url);
    return this.http.post<Patient>(url, patient)
  }

  findById(id: String):Observable<Patient> {
    const url = `${this.baseUrl}pacientes/${id}`
    return this.http.get<Patient>(url)
  }

  delete(id: String): Observable<void> {
    const url = `${this.baseUrl}pacientes/${id}`
    return this.http.delete<void>(url)
  }

  update(id: String, patient: Patient): Observable<void> {
    const url = `${this.baseUrl}pacientes/${id}`
    return this.http.put<void>(url, patient)
  }

  mensagem(str: String): void {
    this._snack.open(`${str}`, 'OK', {
      horizontalPosition: 'end',
      verticalPosition: 'top',
      duration: 3000
    });
  }
}
