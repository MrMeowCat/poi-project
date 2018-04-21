import {Component, OnInit, ViewChild} from '@angular/core';
import { } from '@types/googlemaps';
import { Styles } from "./styles";

@Component({
  selector: 'poi-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {

  constructor() { }

  @ViewChild('gmap') gmapElement: any;
  map: google.maps.Map;

  ngOnInit() {
    let coordinates;

    // navigator.geolocation.getCurrentPosition(location => {
    //   coordinates = location.coords;
    //   this.initMap(coordinates.latitude, coordinates.longitude);
    // }, err => {
    //
    // });
    this.initMap(50.010637, 15.338939);
  }

  initMap(latitude: number, longitude: number) {
    const mapProp = {
      center: new google.maps.LatLng(latitude, longitude),
      zoom: 5,
      minZoom: 4,
      // mapTypeId: google.maps.MapTypeId.ROADMAP,
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

    this.map.mapTypes.set('silver', Styles.styles);
    this.map.setMapTypeId('silver');
  }
}
