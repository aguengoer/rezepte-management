﻿import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
// used to create fake backend
import {ErrorInterceptor, JwtInterceptor} from './_helpers';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AlertComponent} from './_components';
import {HomeComponent} from './home';

;

@NgModule({
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    AlertComponent,
    HomeComponent],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true},

    // provider used to create fake backend
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
};
