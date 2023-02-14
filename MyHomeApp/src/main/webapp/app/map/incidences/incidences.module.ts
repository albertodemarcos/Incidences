import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { RouterModule } from '@angular/router';
import { incidencesRoute } from './incidences-routing.routing';
import { CreateIncidenceComponent } from './create/create-incidence.component';
import { DeleteIncidenceComponent } from './delete/delete-incidence.component';
import { DetailIncidenceComponent } from './detail/detail-incidence.component';
import { ListIncidenceComponent } from './list/list-incidence.component';
import { StatusIncidenceComponent } from './status/status-incidence.component';



@NgModule({
  imports: [SharedModule, RouterModule.forChild(incidencesRoute)],
  declarations: [
    CreateIncidenceComponent,
    DeleteIncidenceComponent,
    DetailIncidenceComponent,
    ListIncidenceComponent,
    StatusIncidenceComponent
  ],
})
export class IncidencesModule { }


