import { AfterViewInit, Component, ElementRef, NgZone, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { NgbModal, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';
import { Geolocation } from 'app/core/model/geolocation.model';
import { IIncidence, Incidence } from 'app/incidences/incidence.model';
import { IncidencesService } from 'app/incidences/incidences.service';
import { Observable, Observer, Subscription } from 'rxjs';
import { GoogleMarkerIncidence, IGoogleMarkerIncidence } from './google-marker-incidence.model';
import { CreateIncidenceModalComponent } from './incidence/create-incidence-modal/create-incidence-modal.component';
import { DetailIncidenceModalComponent } from './incidence/detail-incidence-modal/detail-incidence-modal.component';
import { MapCityService } from './map-city.service';
import { PositionMap } from './position-map.model';

@Component({
  selector: 'jhi-map-city',
  templateUrl: './map-city.component.html',
  styleUrls: ['./map-city.component.scss']
})
export class MapCityComponent implements OnInit, AfterViewInit, OnDestroy {

  public static MARKER_CLUSTERER_IMAGE_PATH = 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m';

  @ViewChild("incidencesMap",{static: false}) public map: google.maps.Map | undefined;

  // @ViewChild("incidencesMap",{static: false}) public mapHtml: ElementRef | undefined;

  options: google.maps.MapOptions | undefined;
  markers: any[];
  // map: google.maps.Map | undefined;
  center: any;
  zoom: number;
  display?: google.maps.LatLngLiteral;
  markerClustererImagePath: any = MapCityComponent.MARKER_CLUSTERER_IMAGE_PATH;;

  private createIncidenceSubscription: Subscription | undefined;
  private updateIncidenceSubscription: Subscription | undefined;

  constructor(private modalService: NgbModal, private mapCityService: MapCityService, public ngZone: NgZone) {
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
  ngAfterViewInit(): void {
    setTimeout(()=>{
      this.initIncidencesMap();
    }, 2000);
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

    this.createIncidenceSubscription = this.mapCityService.getCreateIncidenceSubject().subscribe({
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

    this.updateIncidenceSubscription = this.mapCityService.getUpdateIncidenceSubject().subscribe({
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

  onZoomChanged(): void{
    console.log('onZoomChanged');
    this.viewMarkersOnMap();
  }

  onMapDragend(): void {
    console.log('onMapDragend');
    this.viewMarkersOnMap();
  }
  onBoundsChanged(): void{
    console.log('onBoundsChanged');
    this.viewMarkersOnMap();
  }

/**
 * Private Methods
 */

  private initView(): void {

    this.getUserLocation().subscribe({
      next: (position) => {
        this.center = {
          lat: position.latitude,
          lng:  position.longitude
        };
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
  private initIncidencesMap(): void{

    if( this.map == null || this.map == undefined){
      return;
    }

    this.viewMarkersOnMap();

    let positionMap = this.getPositionMap();
    console.log('positionMap: ' + JSON.stringify( positionMap ) );
  }

  /**
   * 
   * @returns 
   */
  private getPositionMap(): PositionMap | null{
    if( this.map == null || this.map == undefined){
      return null;
    }
    return new PositionMap(
      this.map.getBounds()?.getNorthEast().lat(),
      this.map.getBounds()?.getNorthEast().lng(),
      this.map.getBounds()?.getSouthWest().lat(),
      this.map.getBounds()?.getSouthWest().lng()
    );
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
    ///let markerIncidence: GoogleMarkerIncidence = new GoogleMarkerIncidence(incidence.id, incidence.title, incidence.location);
    let marker = this.createMarker(incidence);
    this.markers.push(marker);
  }

  /**
   * 
   * @param incidence 
   * @returns 
   */
  private createMarker(incidence: GoogleMarkerIncidence): google.maps.Marker{
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

  private viewMarkersOnMap() {

    let positionMap = this.getPositionMap();
    
    this.mapCityService.findAllByPosition(positionMap).subscribe({
      next: (makersIncidence: IGoogleMarkerIncidence[]) => {
        this.markers = [];
        if (!makersIncidence || makersIncidence.length == 0) {
          return;
        }
        for (let i = 0; i < makersIncidence.length; i++) {
          let incidence = makersIncidence[i];
          let marker = this.createMarker(incidence);
          this.markers.push(marker);
        }
      },
      error: (err) => {
        this.markers = [];
        console.log('Se ha producido un error al cargar las incidencias: ' + JSON.stringify(err));
      },
      complete: () => {
        console.info('initIncidencesMap: finalizado');
        setTimeout(()=>{
          this.clusterization(this.markers, this.map);
        }, 3000);
      }
    });
  }

  private clusterization(markers: any, map: any): void {

    /*let options: MarkerClustererOptions = {
      map: this.map,
      markers: this.markers,
    };

    new MarkerClusterer({  });*/
  }


}

