import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, Resolve, Routes } from "@angular/router";
import { Observable, of } from "rxjs";
import { CreateIncidenceComponent } from "./create/create-incidence.component";
import { DetailIncidenceComponent } from "./detail/detail-incidence.component";
import { IIncidence } from "../core/model/incidence.model";
import { IncidencesService } from "./incidences.service";
import { ListIncidenceComponent } from "./list/list-incidence.component";
import { KanbaknIncidenceComponent } from "./kanbakn-incidence/kanbakn-incidence.component";
import { StatusIncidenceComponent } from "./status/status-incidence.component";


@Injectable({ providedIn: 'root' })
export class IncidenceResolve implements Resolve<IIncidence | null> {
  constructor(private service: IncidencesService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IIncidence | null> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id);
    }
    return of(null);
  }
}

export const incidencesRoute: Routes = [
  {
    path: '',
    component: ListIncidenceComponent,
    data: {
      defaultSort: 'id,asc',
    },
  },
  {
    path: ':id/view',
    component: DetailIncidenceComponent,
    resolve: {
      user: IncidenceResolve,
    },
  },
  {
    path: 'create',
    component: CreateIncidenceComponent,
    resolve: {
      user: IncidenceResolve,
    },
  },
  {
    path: ':id/edit',
    component: CreateIncidenceComponent,
    resolve: {
      user: IncidenceResolve,
    },
  },
  {
    path: 'kanban',
    component: KanbaknIncidenceComponent,
    resolve: {
      user: IncidenceResolve,
    },
  },
  {
    path: 'status',
    component: StatusIncidenceComponent,
    resolve: {
      user: IncidenceResolve,
    },
  },
];

