import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Donator } from '../../donator.model';
import { DonatorService } from '../../donator.service';

@Component({
  selector: 'app-update-donator',
  templateUrl: './update-donator.component.html',
  styleUrls: ['./update-donator.component.css']
})
export class UpdateDonatorComponent implements OnInit {

  donator: Donator = {
    nome: "",
    endereco: "",
    tipoSanguineo: "",
    idade: 16,
    cpf: "",
    ultimaDoacao: "",
    telefone: ""
  }

  id: String = '';

  constructor(private service: DonatorService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get("id")!
    this.findById();
  }

  findById() {
    this.service.findById(this.id).subscribe((response) => {
      this.donator.nome = response.nome,
      this.donator.cpf = response.cpf,
      this.donator.endereco = response.endereco,
      this.donator.telefone = response.telefone,
      this.donator.tipoSanguineo = response.tipoSanguineo,
      this.donator.ultimaDoacao = response.ultimaDoacao
    });
  }

  update():void {
    this.service.update(this.id, this.donator).subscribe((response) => {
      this.router.navigate(['listarDoadores'])
      this.service.mensagem("Doador atualizado com sucesso!")
      console.log(this.donator)
    }, err => {
      console.log(err)
    })
  }


}
