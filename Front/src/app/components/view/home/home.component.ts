import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { DonatorService } from '../donator.service';
import { Sangue } from '../sangue.model';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  
  displayedColumns: string[] = ['Teste']
  constructor(private service:DonatorService) { 
    
  }
  ap:number = 0;
  an:number = 0;
  bp:number = 0;
  bn:number = 0;
  abp:number = 0;
  abn:number = 0;
  op:number = 0;
  on:number = 0;
  MenorDoador:String = '';
  
  ngOnInit(): void {
    this.teste()
  }

  findAp(){
    this.service.buscaSangue("A+").subscribe(Response=>{
      this.service.buscaSangue("A+")
      this.ap=Response
    })
    
  }
  findAn(){
    this.service.buscaSangue("A-").subscribe(Response=>{
    this.service.buscaSangue("A-")
    this.an=Response
  })}
  findBp(){this.service.buscaSangue("B+").subscribe(Response=>{
    this.service.buscaSangue("B+")
    this.bp=Response
  })}
  findBn(){this.service.buscaSangue("B-").subscribe(Response=>{
    this.service.buscaSangue("B-")
    this.bn=Response
  })}
  findABp(){this.service.buscaSangue("AB+").subscribe(Response=>{
    this.service.buscaSangue("AB+")
    this.abp=Response
  })}
  findABn(){this.service.buscaSangue("AB-").subscribe(Response=>{
    this.service.buscaSangue("AB-")
    this.abn=Response
  })}
  findOp(){this.service.buscaSangue("O+").subscribe(Response=>{
    this.service.buscaSangue("O+")
    this.op=Response
  })}
  findOn(){this.service.buscaSangue("O-").subscribe(Response=>{
    this.service.buscaSangue("O-")
    this.on=Response
  })}
  
  teste(){
    this.service.MenorDoador().subscribe(Response =>{
      this.MenorDoador = Response
    })
    console.log(this.MenorDoador)
  }

}
