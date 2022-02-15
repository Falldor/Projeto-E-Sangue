import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Donator } from '../../donator.model';
import { DonatorService } from '../../donator.service';

@Component({
  selector: 'app-delete-donator',
  templateUrl: './delete-donator.component.html',
  styleUrls: ['./delete-donator.component.css']
})
export class DeleteDonatorComponent implements OnInit {

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
    this.id = this.route.snapshot.paramMap.get('id')!
    this.findById();
  }

  findById(): void {
    this.service.findById(this.id).subscribe((response) => {
      this.donator = response;
      console.log(this.donator)
    })
  }

  delete(): void {
    this.service.delete(this.id).subscribe((response) => {
      this.router.navigate(['']);
      this.service.mensagem("Doador deletado com sucesso!");
    }, err => {
      this.service.mensagem(err.error.error)
    });
  }


}
