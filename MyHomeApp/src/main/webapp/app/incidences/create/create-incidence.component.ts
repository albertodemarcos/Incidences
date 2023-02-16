import { Component, HostListener, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Incidence } from '../incidence.model';
import { IncidencesService } from '../incidences.service';

@Component({
  selector: 'jhi-create-incidence',
  templateUrl: './create-incidence.component.html'
})
export class CreateIncidenceComponent implements OnInit {

  incidenceForm: FormGroup ;
  incidenceId: any;
  success: boolean = false;
  editForm: boolean = false;
  isSaving: boolean = false;
  files: File[] = [];

  incidencesStatus:Array<string> = ['PENDING','IN_PROCESS','RESOLVED','CANCELED'];

  constructor(  
    private formBuilder: FormBuilder,
    private incidencesService: IncidencesService,
    private router: Router,
    private activatedRoute: ActivatedRoute) {
      this.incidenceForm = this.formBuilder.group({
        id: [null],
        title: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(50)]],
        //startDate: [new Date(), [Validators.required]],
        //endDate: [new Date(), [Validators.required]],
        status: ['PENDING', [Validators.required]],
        photos: [null, [Validators.required]],
        organizationId: [null, [Validators.required]],
        description: ['', ],
        longitude: [0, [Validators.required ]],
        latitude: [0, [Validators.required ]]
      });
  }

  ngOnInit(): void {
    console.log('CreateEditIncidenceComponent => ngOnInit()');

    this.activatedRoute.params.forEach(params => {
      if(!params||!params["id"]){
        return;
      }
      this.incidenceId = +params["id"] || null;
      this.getCreateEditViewModel();
    });
  }

  ngOnDestroy(): void {
    this.editForm = false;
  }

  onSelect(event: any) {
    console.log('onSelect: ' + event);
    this.files.push(...event.addedFiles);
    this.incidenceForm.controls['photos'].setValue(this.files);
  }
  
  onRemove(event: any) {
    console.log('onRemove: ' + event);
    this.files.splice(this.files.indexOf(event), 1);
    this.incidenceForm.controls['photos'].setValue(this.files);
  }

  private getCreateEditViewModel():void {

    this.incidencesService.find(this.incidenceId).subscribe({
      next: (incidenceDto: Incidence) => {
        console.log('Data: ' + JSON.stringify(incidenceDto));
        
        if( !incidenceDto || incidenceDto == null || incidenceDto == undefined ){
          console.error('Error! No data: ');
          return;
        }

        this.editForm = true;
        
        this.incidenceForm = this.formBuilder.group({
          id: [ (incidenceDto.id) ],
          title: [ incidenceDto.title , [Validators.required, Validators.minLength(5), Validators.maxLength(50)]],
          //startDate: [new Date(), [Validators.required]],
          //endDate: [new Date(), [Validators.required]],
          status: [incidenceDto.status, [Validators.required]],
          photos: [null, [Validators.required]],
          organizationId: [null, [Validators.required]],
          description: [incidenceDto.description, [Validators.required, Validators.minLength(15), Validators.maxLength(255)] ],
          longitude: [(incidenceDto.location?.longitude), [Validators.required ]],
          latitude: [(incidenceDto.location?.latitude), [Validators.required ]]
        });
      },
      error: (err: any) => {
        console.error('Error! Don\'t call server');
        console.error('Error code: ' + JSON.stringify(err));
      }
    });

  }
  save(): void {
    console.log('CreateEditIncidenceComponent => createIncidence()');
  }
  
  previousState(): void{
    window.history.back();
  }
}
