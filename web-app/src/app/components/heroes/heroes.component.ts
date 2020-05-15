import { Component, OnInit } from '@angular/core';
import {HeroService} from "../../services/hero/hero.service";
import {RoleService} from "../../services/role/role.service";
import {Observable} from "rxjs";
import {HeroDTO} from "../../dto/hero/HeroDTO";
import {RoleDTO} from "../../dto/role/RoleDTO";

@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.css']
})
export class HeroesComponent implements OnInit {
  heroes: Observable<HeroDTO>;
  roles: Observable<RoleDTO>;
  tmp;
  showModal = false;
  clickedHeroId;

  constructor(private heroService: HeroService,
              private roleService: RoleService) { }

  ngOnInit(): void {
    this.loadHeroes();
    this.loadRoles();
  }

  loadHeroes(){
    this.heroService.getAllHeroes().subscribe(response =>{
      this.tmp = response;
      this.heroes = this.tmp.content;
      console.log(this.heroes);
    });
  }

  loadRoles(){
    this.roleService.getAllRoles().subscribe(response =>{
      this.tmp = response;
      this.roles = this.tmp.content;
    });
  }

  deleteHero(id){
    this.heroService.deleteHero(id).subscribe(response =>{
      this.loadHeroes();
    });
  }

  addRoleModal(heroId){
    this.clickedHeroId = heroId;
    this.showModal = true;

  }
  closeRoleModal(){
    this.clickedHeroId = 0;
    this.showModal = false;
  }
  addRole(roleId){
    this.heroService.addRoleToHero(this.clickedHeroId, roleId).subscribe(response =>{
      this.loadHeroes();
    });
  }

}
