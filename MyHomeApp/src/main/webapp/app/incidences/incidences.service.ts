import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { Pagination } from 'app/core/request/request.model';
import { Observable } from 'rxjs';
import { IIncidence } from './incidence.model';

@Injectable({
  providedIn: 'root'
})
export class IncidencesService {

  private resourceUrl = this.applicationConfigService.getEndpointFor('api/incidences/');

  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(user: IIncidence): Observable<IIncidence> {
    return this.http.post<IIncidence>(this.resourceUrl, user);
  }

  update(user: IIncidence): Observable<IIncidence> {
    return this.http.put<IIncidence>(this.resourceUrl, user);
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

}
