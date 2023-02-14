import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { catchError, map, Observable, of } from 'rxjs';

@Component({
  selector: 'jhi-map-city',
  templateUrl: './map-city.component.html',
  styleUrls: ['./map-city.component.scss']
})
export class MapCityComponent implements OnInit {
  

  apiLoaded: Observable<boolean>;
  //map: google.maps.Map | undefined;

  constructor(httpClient: HttpClient) {
    this.apiLoaded = httpClient.jsonp('https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE', 'callback').pipe(map(() => true),catchError(() => of(false)),);
  }

  ngOnInit(): void {

    this.apiLoaded.subscribe( {

      next: (apiLoaded: boolean)=> {
        console.info('Google maps is loaded');
      },
      error: (error: any)=> {
        console.error('Error! Don\'t load the google maps');
      },
      complete: ()=>{
        console.debug("The google maps is loaded");
      }
    });
    this.initMap();
  }

  private initMap(): void{

    /*this.map = new google.maps.Map(document.getElementById("map") as HTMLElement, {
      center: { lat: -34.397, lng: 150.644 },
      zoom: 8,
    });*/

    /* Buscar forma de cargar mapa
    "@googlemaps/google-maps-services-js": "^3.3.15",
    "@googlemaps/js-api-loader": "^1.14.3",
    */
  }

}

