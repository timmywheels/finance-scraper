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
  private apiUrl = 'http://localhost:4567/api/snapshot/001';
  data: any = [];
  displayedColumns = ['timeStamp', 'symbol', 'companyName', 'lastPrice', 'change', 'percentChange'];
  sortedData: Snapshot[];
  dataSource = new MatTableDataSource(this.sortedData);

  @ViewChild(MatSort) sort: MatSort;

  // ngAfterViewInit() {
  //   this.dataSource.sort = this.sort;
  // }

  ngOnInit() {
    this.sortData(this.data);
  }

  constructor(private http: HttpClient) {
      this.sortedData = this.data.slice()
      console.log("this.sortedData", this.sortedData)
      this.getData();
  }

  getData(){
    return this.http.get(this.apiUrl).subscribe((data) => this.data = data);
  }

  sortData(sort: Sort) {
    const data = this.data.slice();
    if (!sort.active || sort.direction === '') {
      this.data = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'timeStamp': return compare(a.timeStamp, b.timeStamp, isAsc);
        case 'symbol': return compare(a.symbol, b.symbol, isAsc);
        case 'companyName': return compare(a.companyName, b.companyName, isAsc);
        case 'lastPrice': return compare(a.lastPrice, b.lastPrice, isAsc);
        case 'change': return compare(a.change, b.change, isAsc);
        case 'percentChange': return compare(a.percentChange, b.percentChange, isAsc);
        default: return 0;
      }
    });
    console.log("this.sortedData:", this.sortedData)
  }



}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}




