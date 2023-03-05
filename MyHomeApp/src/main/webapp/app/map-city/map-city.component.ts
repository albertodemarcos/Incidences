import { Component, OnInit, ViewChild } from '@angular/core';
import { NgbModal, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';
import { Geolocation } from 'app/core/model/geolocation.model';
import { Incidence } from 'app/incidences/incidence.model';
import { IncidencesService } from 'app/incidences/incidences.service';
import { Observable, Observer } from 'rxjs';
import { CreateIncidenceModalComponent } from './incidence/create-incidence-modal/create-incidence-modal.component';
import { DetailIncidenceModalComponent } from './incidence/detail-incidence-modal/detail-incidence-modal.component';

@Component({
  selector: 'jhi-map-city',
  templateUrl: './map-city.component.html',
  styleUrls: ['./map-city.component.scss']
})
export class MapCityComponent implements OnInit {


  @ViewChild("incidencesMap",{static: false}) public map: any;
   
  //options: MapBaseLayer;

  options: google.maps.MapOptions | undefined;
  markers: any[] = [];

  center = {lat: 40.4948212, lng: -3.3841854};
  zoom = 15;
  display?: google.maps.LatLngLiteral;

  constructor(private modalService: NgbModal, private incidencesService: IncidencesService) {
    
    this.options = {
      mapTypeId: 'hybrid',
      zoomControl: true,
      scrollwheel: false,
      disableDoubleClickZoom: true,
      maxZoom: 15,
      minZoom: 2,
    };
  }

  ngOnInit(): void {
    
    this.getUserLocation().subscribe({
      next: (position) => {
        this.initIncidencesMap(position);
      },
      error: () => {
        console.error('No se ha podido obtener la posicion del usuario');
      },
      complete: ()=> {
        console.info('initIncidencesMap: finalizado');
      }
    });

    /*let marker = {
      position:{
        lat: 40.4948212,
        lng: -3.3841854
      },
      title: 'Punto',
      clickable: true,      
      idIncidence: 50000000,
      options: {
        
      },
    };
    

    this.markers.push(marker);*/
  }

  /**
   * Public Methods
   */

  onMapClick(event: google.maps.MapMouseEvent){
    console.log('onMapClick: ' + event + " latLng: {lat : " + event.latLng?.lat() + ", lng : " + event.latLng?.lng());
    let _options: NgbModalOptions = {
      fullscreen: true
    };
    const _modalCreateIncidenceRef = this.modalService.open(CreateIncidenceModalComponent, _options);
    let _location: Geolocation = new Geolocation(event.latLng?.lat(), event.latLng?.lng());
    _modalCreateIncidenceRef.componentInstance.location = _location;
  }

  onMarkerClick(event: google.maps.MapMouseEvent, idIncidence :any){
    console.log('onMarkerClick: ' + event + "with id = "+idIncidence+" latLng: {lat : " + event.latLng?.lat() + ", lng : " + event.latLng?.lng());
    let _options = {
      fullscreen: true      
    };
    const _modalDetailIncidenceRef = this.modalService.open(DetailIncidenceModalComponent, _options);
    let _location: Geolocation = new Geolocation(event.latLng?.lat(), event.latLng?.lng());
    _modalDetailIncidenceRef.componentInstance.location = _location;
    _modalDetailIncidenceRef.componentInstance.idIncidence = idIncidence;
  }

  private initIncidencesMap(position: any): void{
    this.incidencesService.findAllByPosition(position).subscribe({
      next: (incidences: Incidence[]) => {
        if(!incidences || incidences.length == 0){
          return;
        }
        for(let i = 0; i < incidences.length; i++){
          let incidence = incidences[i];
          let marker = this.createMarker(incidence);
          this.markers.push(marker);
        }
      },
      error: () => {

      },
      complete: ()=> {
        console.info('initIncidencesMap: finalizado');
      }
    });
  }

  private getUserLocation(): Observable<any>{

    return new Observable((observer: Observer<any>) => {

      if (navigator.geolocation) {

        navigator.geolocation.getCurrentPosition((_position) => {
          let positionUser = {
            latitude : _position.coords.latitude,
            longitude : _position.coords.longitude
          };
          observer.next(positionUser);
          observer.complete();
        }, (error: any) => {

          observer.error(error);
        });
      }
      else {
        observer.error(null);
      }
    });
  }

  /*private async getLocationUser(): Promise<any>{

    return new Promise(function(resolve, reject) {
      navigator.geolocation.getCurrentPosition(function(pos){
        let lat = pos.coords.latitude
        let lon = pos.coords.longitude
        resolve({lat,lon});
    });
    
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position)=>{
        const longitude = position.coords.longitude;
        const latitude = position.coords.latitude;
          //this.callApi(longitude, latitude);
      });
    }


  }*/

  private createMarker(incidence: Incidence): google.maps.Marker{
    let json: any = {
      position:{
        lat: incidence.location?.latitude,
        lng: incidence.location?.longitude
      },
      title: incidence.title,
      draggable: true,
      idIncidence: incidence.id
    };
    return new google.maps.Marker(json);
  }



}

