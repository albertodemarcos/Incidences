import { Component, NgZone, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { IncidencesService } from 'app/incidences/incidences.service';
import { Geolocation } from 'app/core/model/geolocation.model';
import { Incidence } from 'app/incidences/incidence.model';
import { Alert, AlertService } from 'app/core/util/alert.service';
import { TranslateService } from '@ngx-translate/core';
import { MapGeocoder, MapGeocoderResponse } from '@angular/google-maps';

@Component({
  selector: 'jhi-create-incidence-modal',
  templateUrl: './create-incidence-modal.component.html',
  styleUrls: ['./create-incidence-modal.component.scss']
})
export class CreateIncidenceModalComponent implements OnInit {

  /**
   * https://www.tabnine.com/code/javascript/classes/googlemaps/GeocoderResult
   */

  incidenceForm: FormGroup ;
  success: boolean = false;
  editForm: boolean = false;
  //isSaving: boolean = false;
  files: File[] = [];
  location: Geolocation | undefined;
  alerts: Alert[] = [];
  photos: any[] = [];

  private alertError: Alert;
  private alertSucces: Alert;
  private geocoder: MapGeocoder;

  incidencesStatus:Array<string> = ['PENDING','IN_PROCESS','RESOLVED','CANCELED'];
  prioritiesStatus:Array<string> = ['LOW','MEDIUM','HIGH'];

  constructor(  
    private activeModal: NgbActiveModal,
    private formBuilder: FormBuilder,
    private incidencesService: IncidencesService,
    private translateService: TranslateService,
    private activatedRoute: ActivatedRoute,
    private ngZone: NgZone) {
      this.incidenceForm = this.formBuilder.group({
        id: [null],
        title: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(50)]],
        //startDate: [new Date(), [Validators.required]],
        //endDate: [new Date(), [Validators.required]],
        status: [{value: 'PENDING', disabled: true}, [Validators.required]],
        priority: ['LOW', [Validators.required] ],
        //photos: [ null , [Validators.required]],
        // organizationId: [null, [Validators.required]],
        description: ['', ],
        longitude: [0, [Validators.required ]],
        latitude: [0, [Validators.required ]]
      });


      this.alertError = { 
        type: 'danger',
        message: this.translateService.instant('incidences.form.errors.create'),
      };

      this.alertSucces = { 
        type: 'success',
        message: this.translateService.instant('incidences.form.create'),
      };

     this.geocoder = new MapGeocoder(ngZone);

  }

  ngOnInit(): void {
    console.log('CreateEditIncidenceComponent => ngOnInit()');

    this.activatedRoute.params.forEach(params => {     
      console.log('params: ' + JSON.stringify(params));
      console.log('location: '  + JSON.stringify(this.location));      
      this.incidenceForm.controls['latitude'].setValue(this.location?.latitude);
      this.incidenceForm.controls['longitude'].setValue(this.location?.longitude);

      this.getLocalidadFromLatLng();
    });
  }

  

  ngOnDestroy(): void {
    this.editForm = false;
  }

  dismiss(): void {
    this.activeModal.dismiss();
  }

  onSelect(event: any) {
    console.log('onSelect: ' + event);
    this.files.push(...event.addedFiles);
    this.photos = this.createPhotosFromFiles();
    //this.incidenceForm.controls['photos'].setValue(this.photos);
  }
  
  onRemove(event: any) {
    console.log('onRemove: ' + event);
    this.files.splice(this.files.indexOf(event), 1);
    this.photos = this.createPhotosFromFiles();
    //this.incidenceForm.controls['photos'].setValue(this.photos);
  }


  save(): void {
    console.log('CreateEditIncidenceComponent => createIncidence()');
    console.log('this.incidenceForm.value: ' + this.incidenceForm.value);
    this.alerts = [];
    this.incidencesService.create( this.incidenceForm.value, this.files).subscribe({
      next: (response: Incidence) => {
        if( response == null ) {
          this.alerts.push(this.alertError);
          return;
        }
        this.alerts.push(this.alertSucces);
        setTimeout(()=>{
          this.dismiss();
        }, 3000);
      },
      error: (error: any) => {
        console.error("Error: "+JSON.stringify(error) );
        this.alerts.push(this.alertError);
      }

    });
  }
  
  previousState(): void{
    window.history.back();
  }

  closeAlert(alert: Alert) {
		this.alerts.splice(this.alerts.indexOf(alert), 1);
	}

  private createPhotosFromFiles() {
    let photos = [];
    for (let i = 0; i < this.files.length; i++) {
      let file = this.files[i];
      let photo = {
        id: null,
        name: '' + file.name,
        file: file
      };
      photos.push(photo);
    }
    return photos;
  }

  private getLocalidadFromLatLng() {
    
    let request: google.maps.GeocoderRequest = this.getLatLng();

    this.geocoder.geocode(request).subscribe({
      next: (response: MapGeocoderResponse) => {
        if( response == undefined ){

          return;
        }

        let address = response.results[0];
        
        let city = address.address_components[address.address_components.length - 2].long_name || '';


       },
      error: (error: any) => { }
    });
  }


  private getLatLng() {
    let latLng = {
      lat: this.location?.latitude ? this.location?.latitude : 0,
      lng: this.location?.longitude ? this.location?.longitude : 0
    };

    let request: google.maps.GeocoderRequest = {
      location: new google.maps.LatLng(latLng)
    };
    return request;
  }
}
