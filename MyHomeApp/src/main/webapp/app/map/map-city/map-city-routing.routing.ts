import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, Resolve, Routes } from "@angular/router";
import { Observable, of } from "rxjs";
import { MapCityComponent } from "./map-city.component";

/*@Injectable({ providedIn: 'root' })
export class IncidenceResolve implements Resolve<IIncidence | null> {
  constructor(private service: IncidencesService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IIncidence | null> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id);
    }
    return of(null);
  }
}*/

export const mapCityRoute: Routes = [  
  {
    path: 'map',
    component: MapCityComponent,
    /*resolve: {
      user: IncidenceResolve,
    },*/
  },  
];

