import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { DashboardComponent }   from './dashboard/dashboard.component';
import { TroopsComponent }       from './troops/troops.component';
import { HeroesComponent }       from './heroes/heroes.component';
import { UsersComponent }       from './users/users.component';
import { RolesComponent }       from './roles/roles.component';


const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'heroes', component: HeroesComponent },
  { path: 'troops', component: TroopsComponent },
  { path: 'users', component: UsersComponent },
  { path: 'roles', component: RolesComponent }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
