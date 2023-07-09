import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { IncidenceDTO } from 'app/core/model/incidenceDTO.model';
import { IncidencesService } from 'app/incidences/incidences.service';
import { MapCityService } from 'app/map-city/map-city.service';

@Component({
  selector: 'jhi-detail-incidence-modal',
  templateUrl: './detail-incidence-modal.component.html',
  styleUrls: ['./detail-incidence-modal.component.scss']
})
export class DetailIncidenceModalComponent implements OnInit {

  //location: Geolocation | undefined;
  //incidence: IncidenceDTO | null = null;
  //idIncidence: string = '';

  statusType: string[] = ['PENDING','IN_PROCESS','PENDING_CONFIRM','RESOLVED','CANCELED'];

  incidence: IncidenceDTO | null = null;
  files: any = [];
  idIncidence: string = '';

  constructor(private activeModal: NgbActiveModal,
    private mapCityService: MapCityService,
    private activatedRoute: ActivatedRoute,
    private incidencesService: IncidencesService, ) 
  {

  }

  ngOnInit(): void {
    console.log('DetailIncidenceModalComponent => ngOnInit()');
    console.log('idIncidence: ' + this.idIncidence);       
    this.getViewModel();
  }

  dismiss(): void {
    this.activeModal.dismiss();
  }

  private getViewModel():void {

    this.mapCityService.find(this.idIncidence).subscribe({
      next: (incidenceDto: IncidenceDTO) => {
        console.log('Data: ' + JSON.stringify(incidenceDto));
        
        if( !incidenceDto || incidenceDto == null || incidenceDto == undefined ){
          console.error('Error! No data: ');
          return;
        }
        this.incidence = incidenceDto;
      },
      error: (err: any) => {
        console.error('Error! Don\'t call server');
        console.error('Error code: ' + JSON.stringify(err));
      }
    });
  }

  public close(){
    this.dismiss();
  }

  public getPhotoFormServe(photo: any): any{
    if( photo == null || photo.id == null || !photo?.bucket || !photo?.imagenUrl ){
      return '';
    }
    return `/api/downloadS3File?bucketName=${photo?.bucket}&fileName=${ photo?.imagenUrl}`;
  }

}
