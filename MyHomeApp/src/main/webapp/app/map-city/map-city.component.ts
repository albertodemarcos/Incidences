import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { NgbModal, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';
import { Geolocation } from 'app/core/model/geolocation.model';
import { IIncidence, Incidence } from 'app/incidences/incidence.model';
import { IncidencesService } from 'app/incidences/incidences.service';
import { Observable, Observer, Subscription } from 'rxjs';
import { CreateIncidenceModalComponent } from './incidence/create-incidence-modal/create-incidence-modal.component';
import { DetailIncidenceModalComponent } from './incidence/detail-incidence-modal/detail-incidence-modal.component';

@Component({
  selector: 'jhi-map-city',
  templateUrl: './map-city.component.html',
  styleUrls: ['./map-city.component.scss']
})
export class MapCityComponent implements OnInit,  OnDestroy {

  @ViewChild("incidencesMap",{static: false}) public map: any;

  options: google.maps.MapOptions | undefined;
  markers: any[]; 

  center: any;
  zoom: number;
  display?: google.maps.LatLngLiteral;

  private createIncidenceSubscription: Subscription | undefined;
  private updateIncidenceSubscription: Subscription | undefined;

  constructor(private modalService: NgbModal, private incidencesService: IncidencesService) {
    this.markers = [];
    this.zoom = 15;
    this.center = {
      lat: 40.4948212,
      lng: -3.3841854
    };
    this.options = {
      mapTypeId: 'hybrid',
      zoomControl: true,
      scrollwheel: false,
      disableDoubleClickZoom: true,
      maxZoom: 15,
      minZoom: 2,
    };
  }
  
  ngOnDestroy(): void {
    if( this.createIncidenceSubscription != null ){
      this.createIncidenceSubscription.unsubscribe();
    }
    if( this.updateIncidenceSubscription != null ){
      this.updateIncidenceSubscription.unsubscribe();
    }
  }

  ngOnInit(): void {
    console.debug('MapCityComponent.ngOnInit()');
    this.initView();

    this.createIncidenceSubscription = this.incidencesService.getCreateIncidenceSubject().subscribe({
      next: (incidence: IIncidence) => {
        if( incidence == null || !incidence.id ){
          return;
        }
        let marker = this.createMarker(incidence);
        this.markers.push(marker);
      },
      error: (err: any) => {
        console.error('Se ha producido un error');
      }
    });

    this.updateIncidenceSubscription = this.incidencesService.getUpdateIncidenceSubject().subscribe({
      next: (incidence: IIncidence) => {
        if( incidence == null || !incidence.id ){
          return;
        }
        this.updateMarkers(incidence);
      },
      error: (err: any) => {
        console.error('Se ha producido un error');
      }
    });
  }

  /**
  * Public Methods
  */

  /**
   * 
   * @param event 
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

  /**
   * 
   * @param event 
   * @param idIncidence 
   */
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

  onMarkerDrag(event: google.maps.MapMouseEvent,){

  }

  onMarkerDragStart(event: google.maps.MapMouseEvent,){

  }

  onMarkerDragEnd(event: google.maps.MapMouseEvent,){

  }


/**
 * Private Methods
 */

  private initView(): void {

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
  }

  /**
   * 
   * @param position 
   */
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
      error: (err) => {
        console.log('Se ha producido un error al cargar las incidencias: ' + JSON.stringify(err) );
      },
      complete: ()=> {
        console.info('initIncidencesMap: finalizado');
      }
    });
  }

  /**
   * 
   * @returns 
   */
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

  /**
   * 
   * @param incidence 
   */
  private updateMarkers(incidence: Incidence): void{
    if( !incidence || !incidence.id ){
      return;
    }
    this.markers.filter( m => {return m.idIncidence !== incidence.id } );
    let marker = this.createMarker(incidence);
    this.markers.push(marker);
  }

  /**
   * 
   * @param incidence 
   * @returns 
   */
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

