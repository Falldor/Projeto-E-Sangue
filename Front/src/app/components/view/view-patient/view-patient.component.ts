import { Component, OnInit } from '@angular/core';
import { Patient } from '../patient.model';
import { PatientService } from '../patient.service';

@Component({
  selector: 'app-view-patient',
  templateUrl: './view-patient.component.html',
  styleUrls: ['./view-patient.component.css']
})
export class ViewPatientComponent implements OnInit {

  patients: Patient[] = [];

  displayedColumns: string[] = ['actions', 'name', 'age', 'bloodtype', 'hospital'];

  constructor(private service: PatientService) { }

  ngOnInit(): void {
    this.findPatients();
  }

  findPatients() {
    this.service.findAll().subscribe(response => {
      console.log(response);
      this.patients = response;
    })
  }


}
