import {Component, OnInit} from '@angular/core';
import {HeroService} from "../../services/hero/hero.service";
import {RoleService} from "../../services/role/role.service";
import {Observable} from "rxjs";
import {HeroDTO} from "../../dto/hero/HeroDTO";
import {RoleDTO} from "../../dto/role/RoleDTO";
import {RoleCreateDTO} from "../../dto/role/RoleCreateDTO";
import {RoleUpdateDTO} from "../../dto/role/RoleUpdateDTO";
import {HeroCreateDTO} from "../../dto/hero/HeroCreateDTO";
import {HeroUpdateDTO} from "../../dto/hero/HeroUpdateDTO";
import {TroopDTO} from "../../dto/troop/TroopDTO";
import {TroopService} from "../../services/troop/troop.service";

@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.css']
})
export class HeroesComponent implements OnInit {
  heroes: HeroDTO[];
  roles: RoleDTO[];
  otherRoles: RoleDTO[];
  troops: TroopDTO[];
  tmp;

  showAddRoleModal = false;
  showShowRoleModal = false;
  showCreateHeroModal = false;
  showUpdateHeroModal = false;
  showJoinTroopModal = false;

  clickedHeroId;
  clickedHero: HeroDTO;

  heroCreateDTO: HeroCreateDTO;
  heroUpdateDTO: HeroUpdateDTO;

  constructor(private heroService: HeroService,
              private roleService: RoleService,
              private troopService: TroopService
  ) {
  }

  ngOnInit(): void {
    this.loadHeroes();
    this.loadRoles();
    this.loadTroops();
    this.heroCreateDTO = new HeroCreateDTO();
    this.heroUpdateDTO = new HeroUpdateDTO();
  }

  // Load data

  loadClickedHero() {
    this.heroService.getHero(this.clickedHeroId).subscribe(response => {
      this.tmp = response;
      this.clickedHero = this.tmp;
    });
  }

  loadHeroes() {
    this.heroService.getAllHeroes().subscribe(response => {
      this.tmp = response;
      this.heroes = this.tmp.content;
    });
  }

  loadTroops() {
    this.troopService.getAllTroops().subscribe(response => {
      this.tmp = response;
      this.troops = this.tmp.content;
    });
  }

  loadRoles() {
    this.roleService.getAllRoles().subscribe(response => {
      this.tmp = response;
      this.roles = this.tmp.content;
    });
  }

  loadOtherRoles(heroId){
    this.heroService.listRolesNotInHero(heroId).subscribe(response => {
      this.tmp = response;
      this.otherRoles = this.tmp.content;
    });
  }

  // Crud

  createHero() {
    this.heroService.createHero(this.heroCreateDTO)
      .subscribe(data => {
        this.loadHeroes();
      });
    this.heroCreateDTO = new HeroCreateDTO();
    this.showCreateHeroModal = false;
  }

  updateHero() {
    this.heroService.updateHero(this.heroUpdateDTO)
      .subscribe(data => {
        this.loadRoles();
      });
    this.heroUpdateDTO = new HeroUpdateDTO();
    this.showUpdateHeroModal = false;
  }

  deleteHero(id) {
    this.heroService.deleteHero(id).subscribe(response => {
      this.loadHeroes();
    });
  }

  addRole(roleId) {
    this.heroService.addRoleToHero(this.clickedHeroId, roleId).subscribe(response => {
      this.loadHeroes();
      this.loadOtherRoles(this.clickedHeroId);
    });
  }

  removeRole(roleId) {
    this.heroService.removeRoleFromHero(this.clickedHeroId, roleId).subscribe(response => {
      this.loadClickedHero();
    });
  }


  joinTroop(troopID) {
    this.heroService.joinTroop(this.clickedHeroId, troopID).subscribe(response => {
      this.loadHeroes();
      this.showJoinTroopModal = false;
    });
  }

  leaveTroop(heroId) {
    this.heroService.leaveTroop(heroId).subscribe(response => {
      this.loadHeroes();
    });
  }

  // Handle Modals

  addRoleModal(heroId) {
    this.loadOtherRoles(heroId);
    this.clickedHeroId = heroId;
    this.showAddRoleModal = true;
  }

  showRoleModal(heroId) {
    this.clickedHeroId = heroId;
    this.loadClickedHero();
    this.showShowRoleModal = true;
  }

  closeAddRoleModal() {
    this.clickedHeroId = 0;
    this.showAddRoleModal = false;
  }

  closeShowRoleModal() {
    this.clickedHeroId = 0;
    this.showShowRoleModal = false;
  }

  createHeroModal() {
    this.showCreateHeroModal = true;
  }

  closeCreateHeroModal() {
    this.showCreateHeroModal = false;
  }

  updateHeroModal() {
    this.showCreateHeroModal = true;
  }

  closeUpdateHeroModal() {
    this.showShowRoleModal = false;
  }

  joinTroopModal(id) {
    this.clickedHeroId = id;
    this.showJoinTroopModal = true;
  }

  closeJoinTroopModal() {
    this.clickedHeroId = 0;
    this.showJoinTroopModal = false;
  }
}
