import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Incidence } from '../incidence.model';
import { IncidenceDTO } from '../incidenceDTO.model';
import { IncidencesService } from '../incidences.service';

@Component({
  selector: 'jhi-detail-incidence',
  templateUrl: './detail-incidence.component.html'
})
export class DetailIncidenceComponent implements OnInit {

  incidenceDTO: IncidenceDTO | null = null;
  idIncidence: string = '';

  constructor(private activatedRoute: ActivatedRoute,
    private incidencesService: IncidencesService,
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
        this.idIncidence = params["id"] || null;
        this.getViewModel();
      },
      error: (err) => {

      }
    });   
  }

  private getViewModel():void {

    this.incidencesService.find(this.idIncidence).subscribe({
      next: (incidenceDto: IncidenceDTO) => {
        console.log('Data: ' + JSON.stringify(incidenceDto));
        
        if( !incidenceDto || incidenceDto == null || incidenceDto == undefined ){
          console.error('Error! No data: ');
          return;
        }
        this.incidenceDTO = incidenceDto;
      },
      error: (err: any) => {
        console.error('Error! Don\'t call server');
        console.error('Error code: ' + JSON.stringify(err));
      }
    });

  }


}
