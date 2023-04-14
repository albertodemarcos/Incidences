import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Incidence } from 'app/incidences/incidence.model';
import { IncidenceDTO } from 'app/incidences/incidenceDTO.model';
import { IncidencesService } from 'app/incidences/incidences.service';
import { MapCityService } from 'app/map-city/map-city.service';

@Component({
  selector: 'jhi-detail-incidence-modal',
  templateUrl: './detail-incidence-modal.component.html',
  styleUrls: ['./detail-incidence-modal.component.scss']
})
export class DetailIncidenceModalComponent implements OnInit {

  location: Geolocation | undefined;
  incidence: IncidenceDTO | null = null;
  idIncidence: string = '';

  constructor(
    private activeModal: NgbActiveModal,
    private activatedRoute: ActivatedRoute,
    private mapCityService: MapCityService,
    private router: Router) 
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

}
