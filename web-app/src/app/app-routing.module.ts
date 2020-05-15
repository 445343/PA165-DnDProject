import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { DashboardComponent }   from './components/dashboard/dashboard.component';
import { TroopsComponent }       from './components/troops/troops.component';
import { HeroesComponent }       from './components/heroes/heroes.component';
import { UsersComponent }       from './components/users/users.component';
import { RolesComponent }       from './components/roles/roles.component';


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
