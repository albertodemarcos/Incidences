import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { IncidencesService } from '../incidences.service';
import { PhotosService } from '../photos.service';
import { IncidenceDTO } from '../../core/model/incidenceDTO.model';

@Component({
  selector: 'jhi-detail-incidence',
  templateUrl: './detail-incidence.component.html',
  styleUrls: ['./detail-incidence.component.scss']
})
export class DetailIncidenceComponent implements OnInit {

  statusType: string[] = ['PENDING','IN_PROCESS','PENDING_CONFIRM','RESOLVED','CANCELED'];

  incidenceDTO: IncidenceDTO | null = null;
  files: any = [];
  idIncidence: string = '';

  constructor(private activatedRoute: ActivatedRoute,
    private incidencesService: IncidencesService,
    private photosService: PhotosService,
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

  public getPhotoFormServe(photo: any): any{
    if( photo == null || photo.id == null || !photo?.bucket || !photo?.imagenUrl ){
      return '';
    }
    return `/api/downloadS3File?bucketName=${photo?.bucket}&fileName=${ photo?.imagenUrl}`;
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
        let photos = incidenceDto.photosDTO ? incidenceDto.photosDTO : [];

        /*for(let i =0; i < photos.length; i++ )
        {
          let photo: any = photos[i];

          this.photosService.find(photo?.bucket, photo?.imagenUrl).subscribe({
            next: (file: any) => {
              console.log('file: ' + file);
            },
            error: (err: any) => {
              //'/api/getS3File?bucketName='+photo.bucket+'&fileName='+photo.imagenUrl;
            }
          });
        }*/

        //console.log('this.photos'+this.photos);
      },
      error: (err: any) => {
        console.error('Error! Don\'t call server');
        console.error('Error code: ' + JSON.stringify(err));
      }
    });

  }


}
