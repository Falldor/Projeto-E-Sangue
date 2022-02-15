import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Patient } from '../../patient.model';
import { PatientService } from '../../patient.service';

@Component({
  selector: 'app-cadastro-paciente',
  templateUrl: './cadastro-paciente.component.html',
  styleUrls: ['./cadastro-paciente.component.css']
})
export class CadastroPacienteComponent implements OnInit {

  patient: Patient = {
    nome: '',
    idade: 1,
    tipoSanguineo: '',
    hospital: '',
  }

  constructor(private service: PatientService, private dialog: MatDialog, private _stack: MatSnackBar) { }

  ngOnInit(): void {
  }

  createPatient(): void {
    this.service.createPatient(this.patient).subscribe((response) => {
      console.log(response);
      this.dialog.closeAll()
      this.service.mensagem("Paciente cadastrado com sucesso!")
    }, err => {
      this.dialog.closeAll();
      this.service.mensagem("Não conseguimos cadastrar o doador, verifique os dados e tente novamente!");
    });
  }

  cancelar(): void {
    this.dialog.closeAll();
  }

}
