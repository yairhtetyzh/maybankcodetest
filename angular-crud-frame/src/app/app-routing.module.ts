import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';
import { LoginformComponent } from './components/loginform/loginform.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { UserformComponent } from './components/userform/userform.component';
import { TransactionListComponent } from './components/transaction/transaction-list/transaction-list.component';
import { TransactionDetailComponent } from './components/transaction/transaction-detail/transaction-detail.component';

const routes: Routes = [
  {path: '', component:DashboardComponent, pathMatch:'full', canActivate: [AuthGuard]},
  {path: 'login', component:LoginformComponent},
  {path: 'dashboard', component:DashboardComponent, canActivate: [AuthGuard]},
  {path: 'userform', component:UserformComponent, canActivate: [AuthGuard]},
  {path: 'transactionlist', component:TransactionListComponent, canActivate: [AuthGuard]},
  {path: 'transaction-edit/:id', component:TransactionDetailComponent, canActivate: [AuthGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
