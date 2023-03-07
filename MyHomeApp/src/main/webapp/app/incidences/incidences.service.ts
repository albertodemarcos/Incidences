import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { Pagination } from 'app/core/request/request.model';
import { map, Observable, Subject } from 'rxjs';
import { IIncidence } from './incidence.model';

@Injectable({
  providedIn: 'root'
})
export class IncidencesService {

  private static URL_ALL_INCIDENCES_USER: String = 'map/list';

  private createIncidence$ = new Subject<IIncidence>(  );
  private updateIncidence$ = new Subject<IIncidence>();

  private resourceUrl = this.applicationConfigService.getEndpointFor('api/incidences/');

  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(user: IIncidence): Observable<IIncidence> {
    return this.http.post<IIncidence>(this.resourceUrl, user).pipe(
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

  find(login: string): Observable<IIncidence> {
    return this.http.get<IIncidence>(`${this.resourceUrl}/${login}`);
  }

  query(req?: Pagination): Observable<HttpResponse<IIncidence[]>> {
    const options = createRequestOption(req);
    return this.http.get<IIncidence[]>(this.resourceUrl, { params: options, observe: 'response' });
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

}
