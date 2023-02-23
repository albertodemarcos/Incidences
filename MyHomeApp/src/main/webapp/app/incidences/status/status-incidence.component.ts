import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints, BreakpointState, MediaMatcher } from '@angular/cdk/layout';
import { Incidence } from '../incidence.model';


@Component({
  selector: 'jhi-status-incidence',
  templateUrl: './status-incidence.component.html',
  styleUrls: ['./status-incidence.component.scss']
})
export class StatusIncidenceComponent implements OnInit {

  toDo = ['incidencia1','incidencia2','incidencia3','incidencia4','incidencia5','incidencia6'];
  
  statusType: string[] = ['PENDING','IN_PROCESS','PENDING_CONFIRM','RESOLVED','CANCELED']; 
  
  list: string[] = ['Camarma', 'Azuqueca', 'Alovera'];
  
  element: string = 'incidence';

  collapse: boolean = true;
  
  sizeDisplay: string = 'phone' || 'tablet' || 'web';

  incidencesPending: any[];
  incidencesInProgress: any[];
  incidencesPendingConfirm: any[];
  incidencesResolved: any[];


  constructor(public breakpointObserver: BreakpointObserver, private mediaMatcher: MediaMatcher) {
    this.mediaQuery();  

    let incidence1 = new Incidence();

    incidence1.id = 1;
    incidence1.title = 'incidencia 1';
    incidence1.startDate = new Date();
    incidence1.photos = [];
    incidence1.status = 'PENDING';
    incidence1.priority = 'HIGH';

    let incidence2 = new Incidence();

    incidence2.id = 2;
    incidence2.title = 'incidencia 2';
    incidence2.startDate = new Date();
    incidence2.photos = [];
    incidence2.status = 'IN_PROCESS';
    incidence2.priority = 'MEDIUM';

    let incidence3 = new Incidence();

    incidence3.id = 3;
    incidence3.title = 'incidencia 3';
    incidence3.startDate = new Date();
    incidence3.photos = [];
    incidence3.status = 'PENDING_CONFIRM';
    incidence3.priority = 'SLOW';

    let incidence4 = new Incidence();

    incidence4.id = 4;
    incidence4.title = 'incidencia 4';
    incidence4.startDate = new Date();
    incidence4.photos = [];
    incidence4.status = 'RESOLVED';
    incidence4.priority = 'SLOW';

    this.incidencesPending = [incidence1, incidence1, incidence1, incidence1];

    this.incidencesInProgress = [incidence2, incidence2, incidence2, incidence2,incidence2,incidence2];

    this.incidencesPendingConfirm = [incidence3, incidence3, incidence3, incidence3,incidence3];

    this.incidencesResolved = [incidence4, incidence4, incidence4, incidence4,incidence4,incidence4,incidence4,incidence4];
  }

  /**
   * kanban
   * https://ej2.syncfusion.com/angular/documentation/kanban/getting-started
   * 
   * https://js.devexpress.com/Demos/WidgetsGallery/Demo/Sortable/Kanban/Angular/Light/
   * 
   * https://www.htmlelements.com/angular/demos/kanban/overview/
   * 
   * https://medium.com/front-end-weekly/getting-started-with-angular-kanban-f2dea404a3df
   * 
   * https://www.creative-tim.com/learning-lab/bootstrap/kanban/argon-dashboard
   * 
   * https://ngchallenges.gitbook.io/example-angular/kanban/kanban/creando-el-kanban#componente-drag-and-drop
   * 
   * https://codescandy.com/geeks-bootstrap-5/pages/dashboard/task-kanban.html
   * 
   * https://wordpress.org/plugins/kanban/#description
   * 
   */

  ngOnInit(): void {
  }

  setCollapse():void{
    console.info('Before collapse: ' + this.collapse);
    this.collapse = !this.collapse;
    console.info('After collapse: ' + this.collapse);
  }

  mediaQuery() {

    //AQUI SERA TRUE SOLO SI ESTA EN RESOLUCION DE PHONE
    this.breakpointObserver.observe([Breakpoints.XSmall, Breakpoints.Small ]).subscribe((state: BreakpointState) => {
      
      console.log('state.breakpoints1: ' + state.breakpoints);
      
      if (state.matches) {
        this.sizeDisplay = 'phone';
      }
    });

    //AQUI SERA TRUE SOLO SI ESTA EN RESOLUCION DE TABLET
    this.breakpointObserver.observe([Breakpoints.Tablet, Breakpoints.Medium]).subscribe((state: BreakpointState) => {
      
      console.log('state.breakpoints2: ' + state.breakpoints);
      
      if (state.matches) {
        this.sizeDisplay = 'tablet';
      }
    });

    //AQUI SERA TRUE SOLO SI ES RESOLUCION PARA WEB
    this.breakpointObserver.observe([Breakpoints.Web, Breakpoints.Large, Breakpoints.XLarge]).subscribe((state: BreakpointState) => {
      
      console.log('state.breakpoints3: ' + state.breakpoints);
      
      if (state.matches) {
        
        this.sizeDisplay = 'web';
      }
    });
  }
}
