import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { Pagination } from 'app/core/request/request.model';
import { map, Observable, Subject } from 'rxjs';
import { IIncidence } from '../incidences/incidence.model';
import { IGoogleMarkerIncidence } from './google-marker-incidence.model';
import { PositionMap } from './position-map.model';

@Injectable({
  providedIn: 'root'
})
export class MapCityService {

  private createIncidence$ = new Subject<IIncidence>(  );
  private updateIncidence$ = new Subject<IIncidence>();

  private resourceUrl = this.applicationConfigService.getEndpointFor('api/markers');

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

  findAllByPosition(position: PositionMap | null): Observable<IGoogleMarkerIncidence[]>{
    let url = `${this.resourceUrl}?aNord=${position?.aNord}&aEst=${position?.aEst}&aSud=${position?.aSud}&aOvest=${position?.aOvest}`;
    return this.http.get<IGoogleMarkerIncidence[]>(url);
  }

  getCreateIncidenceSubject(): Observable<IIncidence> {
    return this.createIncidence$.asObservable();
  }

  getUpdateIncidenceSubject(): Observable<IIncidence> {
    return this.updateIncidence$.asObservable();
  }
}
