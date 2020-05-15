import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs";
import {TroopDTO} from "../../dto/troop/TroopDTO";
import {TroopService} from "../../services/troop/troop.service";

@Component({
  selector: 'app-troops',
  templateUrl: './troops.component.html',
  styleUrls: ['./troops.component.css']
})
export class TroopsComponent implements OnInit {

  troops: Observable<TroopDTO>;
  tmp;

  constructor(private troopService: TroopService) { }

  ngOnInit(): void {
    this.loadTroops();
  }

  loadTroops(){
    this.troopService.getAllTroops().subscribe(response =>{
      this.tmp = response;
      this.troops = this.tmp.content;
    });
  }

  deleteTroop(id){
    this.troopService.deleteTroop(id).subscribe(response =>{
      this.loadTroops();
    });
  }
}
