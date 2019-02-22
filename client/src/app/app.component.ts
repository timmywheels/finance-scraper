import {Component, ViewChild} from '@angular/core';
import { HttpClient } from "@angular/common/http";
import {MatSort, MatTableDataSource, Sort} from '@angular/material';

export interface Snapshot {
  timeStamp: string;
  symbol: string;
  companyName: string;
  lastPrice: string;
  change: string;
  percentChange: string;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],

})



export class AppComponent {
  private apiUrl = 'http://localhost:4567/api/snapshots';
  data: any = [];
  displayedColumns = ['timeStamp', 'symbol', 'companyName', 'lastPrice', 'change', 'percentChange'];
  sortedData: any = [];

  @ViewChild(MatSort) sort: MatSort;

  constructor(private http: HttpClient) {
    this.getData();
    this.sortedData = this.data.slice()
    console.log("this.sortedData", this.sortedData)
  }

  getData(){
    return this.http.get(this.apiUrl).subscribe((data) => this.sortedData = data);
  }

  sortData(sort: Sort) {
    const data = this.sortedData.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'timeStamp': return compare(a.timeStamp, b.timeStamp, isAsc);
        case 'symbol': return compare(a.symbol, b.symbol, isAsc);
        case 'companyName': return compare(a.companyName, b.companyName, isAsc);
        case 'lastPrice': return compare(a.lastPrice, b.lastPrice, isAsc);
        default: return 0;
      }
    });
    console.log("this.sortedData:", this.sortedData)
    return this.sortedData;
  }


  triggerPostRequest(){
    let xhr = new XMLHttpRequest();
    xhr.open('POST', "http://localhost:4567/api/snapshots/new", true);
    xhr.send();
  }

}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}




