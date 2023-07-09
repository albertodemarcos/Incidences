import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { SharedModule } from 'app/shared/shared.module';
import { organizationsRoute } from './organization.route';
import { CreateOrganizationComponent } from './create/create-organization.component';
import { DeleteOrganizationComponent } from './delete/delete-organization.component';
import { DetailOrganizationComponent } from './detail/detail-organization.component';
import { ListOrganizationsComponent } from './list/list-organizations.component';



@NgModule({
  imports: [SharedModule, RouterModule.forChild(organizationsRoute)],
  declarations: [
    CreateOrganizationComponent,
    DeleteOrganizationComponent,
    DetailOrganizationComponent,
    ListOrganizationsComponent
  ],
})
export class OrganizationModule { }
