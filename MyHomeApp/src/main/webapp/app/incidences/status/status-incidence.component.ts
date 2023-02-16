import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'jhi-status-incidence',
  templateUrl: './status-incidence.component.html',
  styleUrls: ['./status-incidence.component.scss']
})
export class StatusIncidenceComponent implements OnInit {

  toDo = ['incidencia1','incidencia2','incidencia3','incidencia4','incidencia5','incidencia6']

  element: string = 'incidence';

  constructor() { }

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
   * https://codescandy.com/geeks-bootstrap-5/pages/dashboard/task-kanban.html
   * 
   * https://ngchallenges.gitbook.io/example-angular/kanban/kanban/creando-el-kanban#componente-drag-and-drop
   * 
   */

  ngOnInit(): void {
  }

}
