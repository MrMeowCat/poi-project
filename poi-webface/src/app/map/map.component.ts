import { Component, OnInit, ViewChild } from '@angular/core';
import { } from '@types/googlemaps';
import { Store } from "@ngrx/store";
import { Observable } from "rxjs/Observable";
import { State } from "../store/states";
import { Theme } from "../shared/models/theme";

@Component({
  selector: 'poi-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {

  @ViewChild('gmap') gmapElement: any;
  map: google.maps.Map;

  selectedTheme$: Observable<Theme>;

  constructor(private store: Store<State>) {
    this.selectedTheme$ = this.store.select(state => state.theme);
  }

  ngOnInit() {
    let coordinates;
    this.initMap(50, 15);
  }

  initMap(latitude: number, longitude: number) {
    const mapProp = {
      center: new google.maps.LatLng(latitude, longitude),
      zoom: 5,
      minZoom: 4,
      mapTypeId: google.maps.MapTypeId.ROADMAP,
      mapTypeControl: false,
      streetViewControl: false,
      fullscreenControl: false,
      draggableCursor: 'default'
    };
    this.map = new google.maps.Map(this.gmapElement.nativeElement, mapProp);

    this.map.addListener('click', e => {
      console.log(e);
    });

    this.map.addListener('zoom_changed', () => {
      console.log(this.map.getZoom());
    });

    this.selectedTheme$.subscribe(theme => {
      let style = theme.style;
      if (typeof theme.style === 'string') {
        style = JSON.parse(style);
      }
      this.map.mapTypes.set(theme.name, new google.maps.StyledMapType(style));
      this.map.setMapTypeId(theme.name);
    });
  }
}
