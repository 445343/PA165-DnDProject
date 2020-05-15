import { Component, OnInit } from '@angular/core';
import {HeroService} from "../../services/hero/hero.service";
import {Observable} from "rxjs";
import {HeroDTO} from "../../dto/hero/HeroDTO";

@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.css']
})
export class HeroesComponent implements OnInit {
  heroes: Observable<HeroDTO>;
  tmp;

  constructor(private heroService: HeroService) { }

  ngOnInit(): void {
    this.loadHeroes();
  }

  loadHeroes(){
    this.heroService.getAllHeroes().subscribe(response =>{
      this.tmp = response;
      this.heroes = this.tmp.content;
    });
  }

  deleteHero(id){
    this.heroService.deleteHero(id).subscribe(response =>{
      this.loadHeroes();
    });
  }

}
