import {BrowserModule, platformBrowser} from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HttpClientModule } from "@angular/common/http";
import { MatTableModule, MatToolbarModule, MatIconModule, MatSortModule, MatButtonModule } from '@angular/material';
import {BrowserAnimationsModule, NoopAnimationsModule} from "@angular/platform-browser/animations";



@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    NoopAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    MatTableModule,
    MatToolbarModule,
    MatIconModule,
    MatSortModule,
    MatButtonModule

  ],
  providers: [AppComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
