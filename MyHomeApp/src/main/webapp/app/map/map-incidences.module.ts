import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { IncidencesModule } from './incidences/incidences.module';
import { MapCityModule } from './map-city/map-city.module';



@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild([
      {
        path: 'incidences',
        loadChildren: () => IncidencesModule,
        data: {
          pageTitle: 'title',
        },
      },
      {
        path: 'map-city',
        loadChildren: ()=> MapCityModule,
      }
    ]),
  ],
})
export class MapIncidencesModule { }
