import { Component, OnInit, ViewChild } from '@angular/core';
import { } from '@types/googlemaps';
import { Store } from "@ngrx/store";
import { Observable } from "rxjs/Observable";
import { State } from "../store/states";
import { Theme } from "../shared/models/theme";
import { Locations } from "../shared/utils/urls";

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
      zoom: 4,
      minZoom: 4,
      mapTypeId: google.maps.MapTypeId.ROADMAP,
      mapTypeControl: false,
      streetViewControl: false,
      fullscreenControl: false,
      draggableCursor: 'default',
      clickableIcons: false
    };
    this.map = new google.maps.Map(this.gmapElement.nativeElement, mapProp);

    this.map.addListener('click', e => this.onMapClick(e));

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



  private onMapClick($event) {
    console.log($event);
    const lat = $event.latLng.lat();
    const lng = $event.latLng.lng();
    const zoom = this.map.getZoom();
    let icon;

    if (zoom < 7) {
      icon = Locations.MARKER_1_PATH;
    } else if (zoom < 9) {
      icon = Locations.MARKER_2_PATH;
    } else if (zoom < 13) {
      icon = Locations.MARKER_3_PATH;
    } else {
      icon = Locations.MARKER_4_PATH;
    }

    const marker = new google.maps.Marker({
      position: {lat, lng},
      map: this.map,
      icon
    });



    const content = `
      <p id="text">
      Lorem ipsum dolor sit amet, quod iisque numquam duo eu. 
      Pro te verear timeam, aeque animal persius an vis, qui ut modo lobortis.
      At pri oratio labores adipisci, audiam urbanitas sed te. Munere gloriatur eloquentiam mel in. 
      Vim putant dolorem adversarium id.

      </p>
    `;

    const infowindow = new google.maps.InfoWindow({
      content
    });

    marker.addListener('click', e => {
      infowindow.open(this.map, marker);
    });

  }
}
