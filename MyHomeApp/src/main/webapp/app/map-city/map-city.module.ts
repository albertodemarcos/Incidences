import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SharedModule } from 'app/shared/shared.module';
import { MapCityComponent } from './map-city.component';
import { mapCityRoute } from './map-city-routing.routing';
import { CreateIncidenceModalComponent } from './incidence/create-incidence-modal/create-incidence-modal.component';
import { UpdateIncidenceModalComponent } from './incidence/update-incidence-modal/update-incidence-modal.component';
import { DetailIncidenceModalComponent } from './incidence/detail-incidence-modal/detail-incidence-modal.component';
import { MapCityService } from './map-city.service';
import { OrganizationService } from 'app/admin/organization-management/organization.service';



@NgModule({
  imports: [SharedModule, RouterModule.forChild(mapCityRoute)],
  declarations: [
    MapCityComponent,
    CreateIncidenceModalComponent,
    UpdateIncidenceModalComponent,
    DetailIncidenceModalComponent
  ],
  providers: [MapCityService, OrganizationService]
})
export class MapCityModule { }
