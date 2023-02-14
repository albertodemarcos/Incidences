import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SharedModule } from 'app/shared/shared.module';
import { MapCityComponent } from './map-city.component';
import { mapCityRoute } from './map-city-routing.routing';



@NgModule({
  imports: [SharedModule, RouterModule.forChild(mapCityRoute)],
  declarations: [
    MapCityComponent
  ],
})
export class MapCityModule { }
