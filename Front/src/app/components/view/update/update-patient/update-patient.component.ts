import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Patient } from '../../patient.model';
import { PatientService } from '../../patient.service';

@Component({
  selector: 'app-update-patient',
  templateUrl: './update-patient.component.html',
  styleUrls: ['./update-patient.component.css']
})
export class UpdatePatientComponent implements OnInit {

  patient: Patient = {
    nome: "",
    tipoSanguineo: "",
    idade: 16,
    hospital: "",
  }

  id: String = '';

  constructor(private service: PatientService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get("id")!
    this.findById();
  }

  findById() {
    this.service.findById(this.id).subscribe((response) => {
      this.patient.nome = response.nome,
      this.patient.tipoSanguineo = response.tipoSanguineo,
      this.patient.hospital = response.hospital,
      this.patient.idade = response.idade
    });
  }

  update():void {
    this.service.update(this.id, this.patient).subscribe((response) => {
      this.router.navigate(['listarPacientes'])
      this.service.mensagem("Paciente atualizado com sucesso!")
    }, err => {
      console.log(this.patient)
      this.service.mensagem("Houve um erro na atualização dos dados do paciente, verifique os dados e tente novamente!")
      console.log(err)
    })
  }

}