import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
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

  constructor(  
    private formBuilder: FormBuilder,
    private organizationService: OrganizationService,
    private router: Router,
    private activatedRoute: ActivatedRoute) {
      this.organizationForm = this.formBuilder.group({
        id: [null],
        name: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(50)]],
        description: ['', ],
        type: ['', [Validators.required]],
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
      this.getViewModel();
    });
  }

  ngOnDestroy(): void {
    this.editForm = false;
  }

  private getViewModel():void {

    this.organizationService.getOrganization(this.organizationId).subscribe({
      next: (response: any) => {
        if( !response || !response.code || response.code.trim() !== '1'){
          console.error('Error! No data: ');
          return;
        }
        this.editForm = true;
        let organizationDto = response.data;
        this.organizationForm = this.formBuilder.group({
          id: [ (+organizationDto.id) ],
          name: [ organizationDto.name , [Validators.required, Validators.minLength(5), Validators.maxLength(50)]],
          description: [organizationDto.description, [Validators.required, Validators.minLength(15), Validators.maxLength(255)] ],
          type: [organizationDto.type, [Validators.required]],
          longitude: [(+organizationDto.longitude), [Validators.required ]],
          latitude: [(+organizationDto.latitude), [Validators.required ]]
        });
      },
      error: (err: any) => {
        console.error('Error! Don\'t call server');
        console.error('Error code: ' + JSON.stringify(err));
      }
    });

  }
  createOrganization(): void {
    console.log('CreateEditOrganizationComponent => createOrganization()');
  }
  

}
