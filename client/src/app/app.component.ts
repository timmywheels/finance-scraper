import { Component } from '@angular/core';
import * as Http from "@angular/common/http";
import {HttpClient} from "@angular/common/http";
import {Resolve} from "@angular/router";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],

})
export class AppComponent {
  title = 'client';
  private apiUrl = 'http://localhost:4567/api/snapshot/001';
  data: any = {};

  constructor(private http: HttpClient) {
      console.log("Hello, API")
      this.getData();
  }

  getData() {
    return this.http.get(this.apiUrl).subscribe((data) => this.data = data);
  }
}


