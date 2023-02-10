import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Organization } from '../organization.model';
import { OrganizationService } from '../organization.service';

@Component({
  selector: 'jhi-detail-organization',
  templateUrl: './detail-organization.component.html'
})
export class DetailOrganizationComponent implements OnInit {

  organization: Organization | null = null;
  idOrganization: string = '';

  constructor(private activatedRoute: ActivatedRoute,
    private organizationService: OrganizationService,
    private router: Router) 
  {

  }

  ngOnInit(): void {
    console.log('CreateEditOrganizationComponent => ngOnInit()');

    this.activatedRoute.params.subscribe({
      next: (params: any) => {
        console.log('CreateEditOrganizationComponent => subscribe()');
        if(!params||!params["id"]){
          return;
        }
        this.idOrganization = params["id"] || null;
        this.getViewModel();
      },
      error: (err) => {

      }
    });   
  }

  private getViewModel():void {

    this.organizationService.find(this.idOrganization).subscribe({
      next: (organizationDto: Organization) => {
        console.log('Data: ' + JSON.stringify(organizationDto));
        
        if( !organizationDto || organizationDto == null || organizationDto == undefined ){
          console.error('Error! No data: ');
          return;
        }
        this.organization = organizationDto;
      },
      error: (err: any) => {
        console.error('Error! Don\'t call server');
        console.error('Error code: ' + JSON.stringify(err));
      }
    });

  }

}
