import { Component, OnInit } from '@angular/core';
import { Donator } from '../donator.model';
import { DonatorService } from '../donator.service';
import { Patient } from '../patient.model';
import { PatientService } from '../patient.service';

@Component({
  selector: 'app-doacao',
  templateUrl: './doacao.component.html',
  styleUrls: ['./doacao.component.css']
})
export class DoacaoComponent implements OnInit {

  options: Donator[] = []
  patients: Patient[] = []
  names: String[] = []
  namesP: String[] = []

  constructor(private serviceD: DonatorService, private serviceP: PatientService) { }

  ngOnInit(): void {
    this.findDonators();
    this.findPatients();
  }

  findDonators() {
    this.serviceD.findAll().subscribe(response => {
      this.options = response;
      response.forEach((value) =>{
        this.names.push(value.nome);
      })
    })
  }

  findPatients() {
    this.serviceP.findAll().subscribe(resposta => {
      console.log(resposta);
      this.patients = resposta;
      resposta.forEach((value) => {
        this.namesP.push(value.nome);
        console.log(this.namesP)
      })
    })
  }

}
