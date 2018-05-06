import { Component, OnInit } from '@angular/core';
import { State } from "../../store/states";
import { Store } from "@ngrx/store";

@Component({
  selector: 'poi-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  isVisible = false;

  constructor(private store: Store<State>) {
    store.select(state => state.sidebarVisible).subscribe(isVisible => {
      this.isVisible = isVisible;
    });
  }

  ngOnInit() {
  }

}
