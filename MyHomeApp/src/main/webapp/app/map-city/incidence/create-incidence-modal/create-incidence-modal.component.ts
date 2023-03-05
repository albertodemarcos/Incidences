import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { IncidencesService } from 'app/incidences/incidences.service';
import { Geolocation } from 'app/core/model/geolocation.model';
import { Incidence } from 'app/incidences/incidence.model';

@Component({
  selector: 'jhi-create-incidence-modal',
  templateUrl: './create-incidence-modal.component.html',
  styleUrls: ['./create-incidence-modal.component.scss']
})
export class CreateIncidenceModalComponent implements OnInit {

  incidenceForm: FormGroup ;
  success: boolean = false;
  editForm: boolean = false;
  //isSaving: boolean = false;
  files: File[] = [];
  location: Geolocation | undefined;

  incidencesStatus:Array<string> = ['PENDING','IN_PROCESS','RESOLVED','CANCELED'];

  constructor(  
    private activeModal: NgbActiveModal,
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
      console.log('params: ' + JSON.stringify(params));
      console.log('location: '  + JSON.stringify(this.location));      
      this.incidenceForm.controls['latitude'].setValue(this.location?.latitude);
      this.incidenceForm.controls['longitude'].setValue(this.location?.longitude);
    });
  }

  ngOnDestroy(): void {
    this.editForm = false;
  }

  dismiss(): void {
    this.activeModal.dismiss();
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


  save(): void {
    console.log('CreateEditIncidenceComponent => createIncidence()');
    console.log('this.incidenceForm.value: ' + this.incidenceForm.value);
    return;
    /*this.incidencesService.create( this.incidenceForm.value).subscribe({
      next: (response: Incidence) => {
        if( response == null ) {
          //let message = response.message != null && response.message.trim() ? response.message: this.translateService.instant('incidences.form.errors.error_persist');
          //this.messageService.add({key: 'toast-modal', severity:'error', summary: 'Error', detail: message});
          return;
        }
       //this.coreService.setCreateIncidence(incidence);
        setTimeout(()=>{
          this.dismiss();
        }, 1000);
      },
      error: (error: any) => {
        console.error("Error: "+JSON.stringify(error) );
        //this.messageService.add({key: 'toast-modal', severity:'error', summary: 'Error', detail: this.translateService.instant('incidences.form.errors.persist') });
      }

    });*/
  }
  
  previousState(): void{
    window.history.back();
  }

}
