import { Component, OnDestroy, OnInit } from '@angular/core';
import { Incidence } from '../../core/model/incidence.model';
import { IncidencesService } from '../incidences.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { IncidenceKanbanDTO } from 'app/core/model/incidenceKanbanDTO.model';
import { Alert } from 'app/core/util/alert.service';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'jhi-kanbakn-incidence',
  templateUrl: './kanbakn-incidence.component.html',
  styleUrls: ['./kanbakn-incidence.component.scss']
})
export class KanbaknIncidenceComponent implements OnInit, OnDestroy {

  statusType: string[] = ['PENDING','IN_PROCESS','PENDING_CONFIRM','RESOLVED','CANCELED'];
  
  incidencesPending: any[] = [];
  incidencesInProgress: any[] = [];
  incidencesPendingConfirm: any[] = [];
  incidencesResolved: any[] = [];  
  alerts: Alert[] = [];

  private alertError: Alert;

  private findAllSubcription: Subscription | undefined;

  constructor( 
    private incidencesService: IncidencesService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private translateService: TranslateService) 
  {
    this.alertError = { 
      type: 'danger',
      message: this.translateService.instant('incidences.form.errors.create'),
    };
  }
 
  ngOnDestroy(): void {
    if(this.findAllSubcription !== undefined ){
      this.findAllSubcription.unsubscribe();
    }
  }

  ngOnInit(): void {
    console.log('KanbaknIncidenceComponent');
    this.init();

    this.findAllSubcription = this.incidencesService.findAll().subscribe({
      next: (incidences: IncidenceKanbanDTO[])=>{
        if(!incidences || incidences.length == 0){
          this.alerts.push(this.alertError);
          return;
        }
        this.initKanban(incidences);
      },
      error: (err) => {
        console.error('Error! Don\'t call server');
        console.error('Error code: ' + JSON.stringify(err));
        throw new Error('Method not implemented.');
      }
    });

  }

  private initKanban(incidences: IncidenceKanbanDTO[])
  {
    if(!incidences || incidences.length == 0){
      return;
    }

    for(let i=0; i<incidences.length;i++){
      let incidence = incidences[i];
      switch(incidence.status){
        case 'PENDING':
          this.incidencesPending.push(incidence);
        break;
        case 'IN_PROCESS':
          this.incidencesInProgress.push(incidence);
        break;
        case 'PENDING_CONFIRM':
          this.incidencesPendingConfirm.push(incidence);
        break;
        case 'RESOLVED':
          this.incidencesResolved.push(incidence);  
        break;
        default:
          break;
      }
    }
  }

  private init(){
    /*let incidence1 = new Incidence();

    incidence1.id = 1;
    incidence1.title = 'incidencia 1';
    incidence1.description = 'Descripcion de la incidencia 1. Esto es un texto largo que vamos a cortar porque queremos ver hasta donde llega el parrafo html';
    incidence1.startDate = new Date();
    //incidence1.photos = [];
    incidence1.status = 'PENDING';
    incidence1.priority = 'HIGH';

    let incidence2 = new Incidence();

    incidence2.id = 2;
    incidence2.title = 'incidencia 2';
    incidence2.description = 'Descripcion de la incidencia 2.';
    incidence2.startDate = new Date();
    //incidence2.photos = [];
    incidence2.status = 'IN_PROCESS';
    incidence2.priority = 'MEDIUM';

    let incidence3 = new Incidence();

    incidence3.id = 3;
    incidence3.title = 'incidencia 3';
    incidence3.description = 'Descripcion de la incidencia 3.';
    incidence3.startDate = new Date();
    //incidence3.photos = [];
    incidence3.status = 'PENDING_CONFIRM';
    incidence3.priority = 'SLOW';

    let incidence4 = new Incidence();

    incidence4.id = 4;
    incidence4.title = 'incidencia 4';
    incidence4.description = 'Descripcion de la incidencia 4.';
    incidence4.startDate = new Date();
    //incidence4.photos = [];
    incidence4.status = 'RESOLVED';
    incidence4.priority = 'SLOW';

    this.incidencesPending = [incidence1, incidence1, incidence1, incidence1];
    */
  }



}
