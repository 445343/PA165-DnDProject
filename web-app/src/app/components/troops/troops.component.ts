import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {TroopDTO} from "../../dto/troop/TroopDTO";
import {TroopService} from "../../services/troop/troop.service";
import {TroopCreateDTO} from "../../dto/troop/TroopCreateDTO";
import {TroopUpdateDTO} from "../../dto/troop/TroopUpdateDTO";
import {UserService} from "../../services/user/user.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-troops',
  templateUrl: './troops.component.html',
  styleUrls: ['./troops.component.css']
})
export class TroopsComponent implements OnInit {
  troops: Observable<TroopDTO>;
  tmp;

  troopCreateDTO: TroopCreateDTO;
  troopUpdateDTO: TroopUpdateDTO;

  showModal = false;
  showShowHeroModal = false;
  mode;

  clickedTroopId: number;
  clickedTroop: TroopDTO;

  isAdmin = false;

  troopCreateForm: FormGroup;

  constructor(private troopService: TroopService, private userService: UserService) {
  }

  ngOnInit(): void {
    this.getAdminStatus();
    this.loadTroops();
    this.troopCreateDTO = new TroopCreateDTO();
    this.troopUpdateDTO = new TroopUpdateDTO();
   }

  loadTroops() {
    this.troopService.getAllTroops().subscribe(response => {
      this.tmp = response;
      this.troops = this.tmp.content;
    });
  }

  loadClickedTroop() {
    this.troopService.getTroop(this.clickedTroopId).subscribe(response => {
      this.tmp = response;
      this.clickedTroop = this.tmp;
    });
  }

  modalPopUp(name) {
    this.showModal = !this.showModal;
    this.mode = name;
  }

  modalPopUpUpdate(id, name, mission, gold) {
    this.showModal = !this.showModal;
    this.mode = 'update';
    this.troopUpdateDTO.id = id;
    this.troopUpdateDTO.name = name;
    this.troopUpdateDTO.mission = mission;
    this.troopUpdateDTO.gold = gold;
  }

  createTroop() {
    this.troopService.createTroop(this.troopCreateDTO)
      .subscribe(data => {
        this.loadTroops();
      });
    this.troopCreateDTO = new TroopCreateDTO();
    this.showModal = false;
  }

  deleteTroop(id) {
    this.troopService.deleteTroop(id).subscribe(response => {
      this.loadTroops();
    });
  }

  updateTroop() {
    this.troopService.updateTroop(this.troopUpdateDTO)
      .subscribe(data => {
        this.loadTroops();
      });
    this.troopUpdateDTO = new TroopUpdateDTO();
    this.showModal = false;
  }

  showHeroModal(troopId) {
    this.clickedTroopId = troopId;
    this.loadClickedTroop();
    this.showShowHeroModal = true;
  }

  closeShowHeroModal() {
    this.clickedTroopId = 0;
    this.showShowHeroModal = false;
  }

  getAdminStatus() {
    this.userService.isAdmin().subscribe(response => {
      this.isAdmin = response;
    })
  }

}
