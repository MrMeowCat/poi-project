import {Component, OnInit, ViewChild} from '@angular/core';
import { } from '@types/googlemaps';

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

    navigator.geolocation.getCurrentPosition(location => {
      coordinates = location.coords;
      var mapProp = {
        center: new google.maps.LatLng(coordinates.latitude, coordinates.longitude),
        zoom: 5,
        minZoom: 4,
        mapTypeId: google.maps.MapTypeId.ROADMAP
      };
      this.map = new google.maps.Map(this.gmapElement.nativeElement, mapProp);
    });
  }
}
