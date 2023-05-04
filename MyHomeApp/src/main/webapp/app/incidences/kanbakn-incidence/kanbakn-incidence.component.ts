import { Component, OnDestroy, OnInit } from '@angular/core';
import { Incidence } from '../../core/model/incidence.model';
import { IncidencesService } from '../incidences.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { IncidenceKanbanDTO } from 'app/core/model/incidenceKanbanDTO.model';
import { Alert } from 'app/core/util/alert.service';
import { TranslateService } from '@ngx-translate/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UpdateEmployeeIncidenceComponent } from '../update-employee-incidence/update-employee-incidence.component';
import { DragulaService } from 'ng2-dragula';

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
  //private dragulaSubcription: Subscription;
  //private dragSubcription: Subscription;
  private dropModelSubcription: Subscription;

  constructor( 
    private incidencesService: IncidencesService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private modalService: NgbModal,
    private translateService: TranslateService,
    private dragulaService: DragulaService) 
  {
    this.alertError = { 
      type: 'danger',
      message: this.translateService.instant('incidences.form.errors.create'),
    };
    
    this.dropModelSubcription = this.dragulaService.dropModel("INCIDENCES_CONTAINER").subscribe(
      ({ name: string, el: Element, target, source, sibling, item, sourceModel, targetModel, sourceIndex, targetIndex }) => {
      console.log('dropModel event!!!');
      if( !item || !item.id  ){
        throw new Error('Incidence is wrong!.');
      }
      //Get element
      let incidence = targetModel[targetIndex];
      //Update element

    });

  }
  ngOnDestroy(): void {
    //throw new Error('Method not implemented.');
    if(this.findAllSubcription !== undefined ){
      this.findAllSubcription.unsubscribe();
    }
    if( this.dropModelSubcription !== undefined ){
      this.dropModelSubcription.unsubscribe();
    }
  }

  ngOnInit(): void {
    console.log('KanbaknIncidenceComponent');
    this.initIncidences();
  }

  private initIncidences() {
    this.findAllSubcription = this.incidencesService.findAll().subscribe({
      next: (incidences: IncidenceKanbanDTO[]) => {
        if (!incidences || incidences.length == 0) {
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

  getIncidence(id: any): void{
    this.router.navigateByUrl(`/incidences/${id}/view`);
  }

  updateEmployeeIncidence(incidence: IncidenceKanbanDTO){
    const modalRef = this.modalService.open(UpdateEmployeeIncidenceComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.incidence = incidence;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.initIncidences();
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
      this.processIncidence(incidence);
    }
    this.initDragAndDrop();
  }

  private processIncidence(incidence: IncidenceKanbanDTO) {
    switch (incidence.status) {
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

  private initDragAndDrop(): void{
    
    this.dragulaService.createGroup('INCIDENCES_CONTAINER', {
      accepts: (el?: Element | undefined, target?: Element | undefined, source?: Element | undefined, sibling?: Element | undefined)=> {
        console.debug('accepts');
        return this.accept(el, target, source, sibling);
      },
      /*copyItem: (item: any) => {
        return item;
      },*/
      
    });

    /*this.dragulaSubcription.add(this.dragulaService.drag("INCIDENCES_CONTAINER").subscribe(({ name, el, source }) => {
      console.log('drag event!!!');
    }));



    this.dragulaSubcription.add(this.dragulaService.drop("INCIDENCES_CONTAINER").subscribe(({ name, el, target, source, sibling }) => {
        console.log('drop event!!!');
      })
    );
    // some events have lots of properties, just pick the ones you need
    this.dragulaSubcription.add(this.dragulaService.dropModel("INCIDENCES_CONTAINER").subscribe(({ sourceModel, targetModel, item }) => {
      console.log('dropModel event!!!');
    }));*/
    
  }

  private accept(el?: Element, target?: Element, source?: Element, sibling?: Element): boolean{
    return target?.id !== 'source' ;
  }

  


}
