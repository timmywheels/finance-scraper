import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {MatTableDataSource} from "@angular/material";
import {PageEvent} from "@angular/material/typings/esm5/paginator";
import {MatToolbar} from "@angular/material";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],

})


export class AppComponent {
  private apiUrl = 'http://localhost:4567/api/snapshot/001';
  data: any = {};
  displayedColumns: string[] = ['date', 'symbol', 'companyName', 'lastPrice', 'change', 'percentChange'];
  dataSource = new MatTableDataSource(this.data);

  constructor(private http: HttpClient) {
      console.log("Hello, API")
      this.getData();
  }

  length = 100;
  pageSize = 10;
  pageSizeOptions: number[] = [5, 10, 25, 100];

  // MatPaginator Output
  pageEvent: PageEvent;

  setPageSizeOptions(setPageSizeOptionsInput: string) {
    this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
  }


  getData() {
    return this.http.get(this.apiUrl).subscribe((data) => this.data = data);
  }

}


