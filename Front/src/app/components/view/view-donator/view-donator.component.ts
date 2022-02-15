import { Component, OnInit } from '@angular/core';
import { Donator } from '../donator.model';
import { DonatorService } from '../donator.service';

@Component({
  selector: 'app-view-donator',
  templateUrl: './view-donator.component.html',
  styleUrls: ['./view-donator.component.css']
})
export class ViewDonatorComponent implements OnInit {

  donators: Donator[] = [];

  displayedColumns: string[] = ['actions', 'name', 'age', 'bloodtype','lastdonation',];

  constructor(
    private service: DonatorService
  ) { }

  ngOnInit(): void {
    this.findDonators();
  }

  findDonators() {
    this.service.findAll().subscribe(response => {
      console.log(response);
      this.donators = response;
    })
  }
}
