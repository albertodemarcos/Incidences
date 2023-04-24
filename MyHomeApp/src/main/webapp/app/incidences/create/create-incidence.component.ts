import { Component, HostListener, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Incidence } from '../incidence.model';
import { IncidencesService } from '../incidences.service';
import { IncidenceDTO } from '../incidenceDTO.model';
import { Alert } from 'app/core/util/alert.service';
import { TranslateService } from '@ngx-translate/core';

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
  alerts: Alert[] = [];
  
  private alertError: Alert;
  private alertSucces: Alert;

  incidencesStatus:Array<string> = ['PENDING','IN_PROCESS','RESOLVED','CANCELED'];
  prioritiesStatus:Array<string> = ['LOW','MEDIUM','HIGH'];

  constructor(private formBuilder: FormBuilder, private incidencesService: IncidencesService,
    private router: Router, private translateService: TranslateService, private activatedRoute: ActivatedRoute) {
    
      this.incidenceForm = this.formBuilder.group({
      id: [null],
      title: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(50)]],
      //startDate: [new Date(), [Validators.required]],
      //endDate: [new Date(), [Validators.required]],
      status: ['PENDING', [Validators.required]],
      priority: ['LOW', [Validators.required] ],
      organizationId: [null, [Validators.required]],
      description: ['', ],
      longitude: [0, [Validators.required ]],
      latitude: [0, [Validators.required ]]
    });

    this.alertError = { 
      type: 'danger',
      message: this.translateService.instant('incidences.form.errors.create'),
    };

    this.alertSucces = { 
      type: 'success',
      message: this.translateService.instant('incidences.form.create'),
    };
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
      next: (incidenceDto: IncidenceDTO) => {
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
          priority: [incidenceDto.priority, [Validators.required] ],
          organizationId: [null/*, [Validators.required]*/],
          description: [incidenceDto.description, [Validators.required, Validators.minLength(15), Validators.maxLength(255)] ],
          longitude: [(incidenceDto.longitude), [Validators.required ]],
          latitude: [(incidenceDto.latitude), [Validators.required ]]
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
    console.log('this.incidenceForm.value: ' + this.incidenceForm.value);
    this.alerts = [];
    this.incidencesService.update( this.incidenceForm.value).subscribe({
      next: (response: Incidence) => {
        if( response == null ) {
          this.alerts.push(this.alertError);
          return;
        }
        this.alerts.push(this.alertSucces);
        this.router.navigateByUrl('/incidences');
      },
      error: (error: any) => {
        console.error("Error: "+JSON.stringify(error) );
        this.alerts.push(this.alertError);
      }

    });
  }
  
  previousState(): void{
    window.history.back();
  }
}
