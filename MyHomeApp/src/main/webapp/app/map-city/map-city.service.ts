import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { Pagination } from '../core/request/request.model';
import { map, Observable, Subject } from 'rxjs';
import { IIncidence } from '../core/model/incidence.model';
import { IGoogleMarkerIncidence } from './google-marker-incidence.model';
import { PositionMap } from './position-map.model';
import { IIncidenceDTO } from 'app/core/model/incidenceDTO.model';

@Injectable({
  providedIn: 'root'
})
export class MapCityService {

  private createIncidence$ = new Subject<IIncidence>(  );
  private updateIncidence$ = new Subject<IIncidence>();

  private resourceUrl = this.applicationConfigService.getEndpointFor('api/incidences');
  private resourceUrlMarkers = this.applicationConfigService.getEndpointFor('api/markers');


  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(incidence: IIncidence, photos: File[]): Observable<IIncidence> {
    let url = `${this.resourceUrl}/create`;
    let options = this.getHeadersFile();
    let formData: FormData = this.createIncidence(incidence, photos);
    return this.http.post<IIncidence>(url, formData, options ).pipe(
      map((userSave: IIncidence ) => {
        this.createIncidence$.next(userSave);
        return userSave;
      })      
    );
  }

  update(user: IIncidence): Observable<IIncidence> {
    return this.http.put<IIncidence>(this.resourceUrl, user).pipe(
      map((userSave: IIncidence ) => {
        this.updateIncidence$.next(userSave);
        return userSave;
      })      
    );
  }

  find(idIncidenceStr: string): Observable<IIncidence> {
    return this.http.get<IIncidenceDTO>(`${this.resourceUrl}/${idIncidenceStr}`);
  }

  query(req?: Pagination): Observable<HttpResponse<IIncidence[]>> {
    const options = createRequestOption(req);
    return this.http.get<IIncidence[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(login: string): Observable<{}> {
    return this.http.delete(`${this.resourceUrl}/${login}`);
  }

  findAllByPosition(position: PositionMap | null): Observable<IGoogleMarkerIncidence[]>{
    let url = `${this.resourceUrlMarkers}?aNord=${position?.aNord}&aEst=${position?.aEst}&aSud=${position?.aSud}&aOvest=${position?.aOvest}`;
    return this.http.get<IGoogleMarkerIncidence[]>(url);
  }


  /**
   * Observables
   */

  getCreateIncidenceSubject(): Observable<IIncidence> {
    return this.createIncidence$.asObservable();
  }

  getUpdateIncidenceSubject(): Observable<IIncidence> {
    return this.updateIncidence$.asObservable();
  }

  private createIncidence(incidence: any, photos: File[]) {

    console.log('createIncidence');

    let formData: FormData = new FormData();

    formData.append('title', incidence.title || '' );
    formData.append('description', incidence.description || '' );
    formData.append('idOrganization', incidence.idOrganization || '' );
    formData.append('status', incidence.status || '' );
    formData.append('priority', incidence.priority || '' );
    formData.append('longitude', JSON.stringify(incidence.longitude) || '' );
    formData.append('latitude', JSON.stringify(incidence.latitude) || '' );
    //formData.append('startDate', this.getDateStrNow() );

    for (let i = 0; i < photos.length; i++) {
      let photo = photos[0];
      formData.append('photos', photo, photo.name);
    }
    return formData;
  }

  private getHeadersFile() {
    let headers = new HttpHeaders();

    headers.append('Content-Type', 'multipart/form-data');
    headers.append('Accept', 'application/json');

    let options = { headers: headers };
    return options;
  }

  /*private getDateStrNow(): string{

    let dateNow = new Date().getMilliseconds().toString();//.toISOString();

    return dateNow;
  }*/
}
