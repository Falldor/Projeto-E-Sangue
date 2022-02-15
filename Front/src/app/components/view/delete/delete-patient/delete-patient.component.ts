import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Patient } from '../../patient.model';
import { PatientService } from '../../patient.service';

@Component({
  selector: 'app-delete-patient',
  templateUrl: './delete-patient.component.html',
  styleUrls: ['./delete-patient.component.css']
})
export class DeletePatientComponent implements OnInit {

  patient: Patient = {
    nome: "",
    hospital: "",
    tipoSanguineo: "",
    idade: 16
  }

  id: String = '';

  constructor(private service: PatientService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id')!
    this.findById();
  }

  findById(): void {
    this.service.findById(this.id).subscribe((response) => {
      this.patient = response;
      console.log(this.patient)
    })
  }

  delete(): void {
    this.service.delete(this.id).subscribe((response) => {
      this.router.navigate(['']);
      this.service.mensagem("Paciente deletado com sucesso!");
    }, err => {
      this.service.mensagem(err.error.error)
    });
  }

  

}
