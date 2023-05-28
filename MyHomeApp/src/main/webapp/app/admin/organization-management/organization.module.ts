import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { SharedModule } from 'app/shared/shared.module';
import { organizationsRoute } from './organization.route';
import { CreateOrganizationComponent } from './create/create-organization.component';
import { DeleteOrganizationComponent } from './delete/delete-organization.component';
import { DetailOrganizationComponent } from './detail/detail-organization.component';
import { ListOrganizationsComponent } from './list/list-organizations.component';
import { ImporterComponent } from './importer/importer.component';



@NgModule({
  imports: [SharedModule, RouterModule.forChild(organizationsRoute)],
  declarations: [
    CreateOrganizationComponent,
    DeleteOrganizationComponent,
    DetailOrganizationComponent,
    ListOrganizationsComponent,
    ImporterComponent
  ],
})
export class OrganizationModule { }
