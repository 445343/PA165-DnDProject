import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs";
import {TroopDTO} from "../../dto/troop/TroopDTO";
import {TroopService} from "../../services/troop/troop.service";
import {TroopCreateDTO} from "../../dto/troop/TroopCreateDTO";

@Component({
  selector: 'app-troops',
  templateUrl: './troops.component.html',
  styleUrls: ['./troops.component.css']
})
export class TroopsComponent implements OnInit {
  troops: Observable<TroopDTO>;
  tmp;

  troopCreateDTO: TroopCreateDTO;

  showModal = false;
  mode;

  constructor(private troopService: TroopService) { }

  ngOnInit(): void {
    this.loadTroops();
    this.troopCreateDTO = new TroopCreateDTO();
  }

  loadTroops(){
    this.troopService.getAllTroops().subscribe(response =>{
      this.tmp = response;
      this.troops = this.tmp.content;
    });
  }

  modalPopUp(name){
    this.showModal = !this.showModal;
    this.mode = name;
  }

  createTroop(){
    this.troopService.createTroop(this.troopCreateDTO)
      .subscribe(data => {this.loadTroops();
      });
    this.troopCreateDTO = new TroopCreateDTO();
    this.showModal = false;
  }

  deleteTroop(id){
    this.troopService.deleteTroop(id).subscribe(response =>{
      this.loadTroops();
    });
  }
}
