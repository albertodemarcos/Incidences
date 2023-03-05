import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Incidence } from 'app/incidences/incidence.model';
import { IncidencesService } from 'app/incidences/incidences.service';

@Component({
  selector: 'jhi-detail-incidence-modal',
  templateUrl: './detail-incidence-modal.component.html',
  styleUrls: ['./detail-incidence-modal.component.scss']
})
export class DetailIncidenceModalComponent implements OnInit {

  location: Geolocation | undefined;
  incidence: Incidence | null = null;
  idIncidence: string = '';

  constructor(
    private activeModal: NgbActiveModal,
    private activatedRoute: ActivatedRoute,
    private incidencesService: IncidencesService,
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

    this.incidencesService.find(this.idIncidence).subscribe({
      next: (incidenceDto: Incidence) => {
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
