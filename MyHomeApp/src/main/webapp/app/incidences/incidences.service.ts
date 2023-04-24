import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { Pagination } from 'app/core/request/request.model';
import { map, Observable, Subject } from 'rxjs';
import { IIncidence } from './incidence.model';
import { IIncidenceDTO } from './incidenceDTO.model';
import { IIncidenceListDTO } from './incidenceListDTO.model';

@Injectable({
  providedIn: 'root'
})
export class IncidencesService {

  private static URL_ALL_INCIDENCES_USER: String = 'map/list';

  private createIncidence$ = new Subject<IIncidence>(  );
  private updateIncidence$ = new Subject<IIncidence>();

  private resourceUrl = this.applicationConfigService.getEndpointFor('/api/incidences');

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
  update(incidence: IIncidenceDTO): Observable<IIncidenceDTO> {
    return this.http.put<IIncidenceDTO>(this.resourceUrl, incidence).pipe(
      map((incidenceSave: IIncidenceDTO ) => {
        this.updateIncidence$.next(incidenceSave);
        return incidenceSave;
      })      
    );
  }

  find(idIncidenceStr: string): Observable<IIncidenceDTO> {
    return this.http.get<IIncidenceDTO>(`${this.resourceUrl}/${idIncidenceStr}`);
  }

  query(req?: Pagination): Observable<HttpResponse<IIncidenceListDTO[]>> {
    const options = createRequestOption(req);
    return this.http.get<IIncidenceListDTO[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(login: string): Observable<{}> {
    return this.http.delete(`${this.resourceUrl}/${login}`);
  }

  findAllByPosition(position: any): Observable<IIncidence[]>{
    let url = `${this.resourceUrl}/${IncidencesService.URL_ALL_INCIDENCES_USER}`;
    url += `?latitude=${position?.latitude}&longitude=${position?.longitude}`;
    return this.http.get<IIncidence[]>(url);
  }

  getCreateIncidenceSubject(): Observable<IIncidence> {
    return this.createIncidence$.asObservable();
  }

  getUpdateIncidenceSubject(): Observable<IIncidence> {
    return this.updateIncidence$.asObservable();
  }

  private createIncidence(incidence: IIncidence, photos: File[]) {
    let formData: FormData = new FormData();
    formData.append('incidence', JSON.stringify(incidence));
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
}
