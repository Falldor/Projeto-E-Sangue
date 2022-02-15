import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { CadastroDoadorComponent } from '../../view/cadastro/cadastro-doador/cadastro-doador.component';
import { CadastroPacienteComponent } from '../../view/cadastro/cadastro-paciente/cadastro-paciente.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(
    private router: Router,
    private dialog: MatDialog,
  ) { }

  ngOnInit(): void {
  }

  navHome(){
    this.router.navigate([""])
  }

  navCadastraDoador(){
    this.dialog.open(CadastroDoadorComponent, {
      width:"60%"
    })
  }

  navListaDoadores() {
    this.router.navigate(["visualizarDoadores"])
  }

  navListDoadores() {
    this.router.navigate(["listarDoadores"])
  }

  navCadastraPaciente() {
    this.dialog.open(CadastroPacienteComponent, {
      width: "60%"
    })
  }

  navListaPacientes() {
    this.router.navigate(["listarPacientes"])
  }

  navDoacoes(){
    this.router.navigate(["registraDoacao"])
  }
  

}
