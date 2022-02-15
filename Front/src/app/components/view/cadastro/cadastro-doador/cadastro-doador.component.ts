import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Donator } from '../../donator.model';
import { DonatorService } from '../../donator.service';
import {formatDate} from '@angular/common';
import { LOCALE_ID, Inject } from '@angular/core';


@Component({
  selector: 'app-cadastro-doador',
  templateUrl: './cadastro-doador.component.html',
  styleUrls: ['./cadastro-doador.component.css']
})
export class CadastroDoadorComponent implements OnInit {

  date: string = '2017-09-12';

  donator: Donator = {
    nome: '',
    cpf: '',
    idade : 16,
    endereco: '', // coloquei a idade mínima
    telefone: "",
    tipoSanguineo: '',
    ultimaDoacao: "",
  }

  formataData(data: string): string {
    return formatDate(data,'dd-MM-yyyy', this.locale);  
  }

  constructor(private service: DonatorService, private dialog: MatDialog, @Inject(LOCALE_ID) public locale: string) { }

  ngOnInit(): void {
  }

  createDonator(): void {
    this.donator.ultimaDoacao = this.formataData(this.donator.ultimaDoacao)
    console.log(this.donator.ultimaDoacao)
    this.service.createDonator(this.donator).subscribe((response) => {
      console.log(response);
      this.dialog.closeAll();
      this.service.mensagem("Doador cadastrado com sucesso!");
    }, err => {
      this.dialog.closeAll();
      console.log(this.donator.ultimaDoacao)
      this.service.mensagem("Não conseguimos cadastrar o doador, verifique os dados e tente novamente!");
    });
  }

  cancelar(): void {
    this.dialog.closeAll();
  }

}
