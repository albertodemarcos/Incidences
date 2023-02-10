import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { IOrganization, Organization } from '../organization.model';
import { OrganizationService } from '../organization.service';

@Component({
  selector: 'jhi-create-organization',
  templateUrl: './create-organization.component.html'
})
export class CreateOrganizationComponent implements OnInit {

  organizationForm: FormGroup ;
  organizationId: any;
  success: boolean = false;
  editForm: boolean = false;
  isSaving: boolean = false;

  organizationsTypes:Array<string> = ['TOWN_HALL','COMMONWEALTH','ADMINISTRATIVE_REGIONS','STATE','CONFEDERATION','PRINCIPAL'];

  constructor(  
    private formBuilder: FormBuilder,
    private organizationService: OrganizationService,
    private router: Router,
    private activatedRoute: ActivatedRoute) {
      this.organizationForm = this.formBuilder.group({
        id: [null],
        name: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(50)]],
        description: ['', ],
        type: ['TOWN_HALL', [Validators.required]],
        longitude: [0, [Validators.required ]],
        latitude: [0, [Validators.required ]]
      });
  }

  ngOnInit(): void {
    console.log('CreateEditOrganizationComponent => ngOnInit()');

    this.activatedRoute.params.forEach(params => {
      if(!params||!params["id"]){
        return;
      }
      this.organizationId = +params["id"] || null;
      this.getCreateEditViewModel();
    });
  }

  ngOnDestroy(): void {
    this.editForm = false;
  }

  private getCreateEditViewModel():void {

    this.organizationService.find(this.organizationId).subscribe({
      next: (organizationDto: Organization) => {
        console.log('Data: ' + JSON.stringify(organizationDto));
        
        if( !organizationDto || organizationDto == null || organizationDto == undefined ){
          console.error('Error! No data: ');
          return;
        }

        this.editForm = true;
        
        this.organizationForm = this.formBuilder.group({
          id: [ (organizationDto.id) ],
          name: [ organizationDto.name , [Validators.required, Validators.minLength(5), Validators.maxLength(50)]],
          description: [organizationDto.description, [Validators.required, Validators.minLength(15), Validators.maxLength(255)] ],
          type: [organizationDto.type, [Validators.required]],
          longitude: [(organizationDto.geolocation?.longitude), [Validators.required ]],
          latitude: [(organizationDto.geolocation?.latitude), [Validators.required ]]
        });
      },
      error: (err: any) => {
        console.error('Error! Don\'t call server');
        console.error('Error code: ' + JSON.stringify(err));
      }
    });

  }
  save(): void {
    console.log('CreateEditOrganizationComponent => createOrganization()');
  }
  
  previousState(): void{
    window.history.back();
  }
}
