import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule,HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AngularMaterialModule } from './angular-material.module';
import { LoginformComponent } from './components/loginform/loginform.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ErrorMessageComponent } from './components/error-message/error-message.component';
import { UserformComponent } from './components/userform/userform.component';
import { TokenInterceptorService } from './../app/interceptors/token-interceptor.service';
import { TransactionListComponent } from './components/transaction/transaction-list/transaction-list.component';
import { TransactionDetailComponent } from './components/transaction/transaction-detail/transaction-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    DashboardComponent,
    TransactionListComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    AngularMaterialModule,
    HttpClientModule,
    ErrorMessageComponent,
    UserformComponent,
    LoginformComponent,
    TransactionDetailComponent
  ],
  providers: [
    {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptorService,
    multi: true,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
