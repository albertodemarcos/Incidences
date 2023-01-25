import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { Pagination } from 'app/core/request/request.model';
import { Observable } from 'rxjs';
import { IOrganization } from './organization.model';

@Injectable({
  providedIn: 'root'
})
export class OrganizationService {

  private resourceUrl = this.applicationConfigService.getEndpointFor('api/admin/organizations');

  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(user: IOrganization): Observable<IOrganization> {
    return this.http.post<IOrganization>(this.resourceUrl, user);
  }

  update(user: IOrganization): Observable<IOrganization> {
    return this.http.put<IOrganization>(this.resourceUrl, user);
  }
  find(idOrganizationStr: string): Observable<IOrganization> {
    return this.http.get<IOrganization>(`${this.resourceUrl}/${idOrganizationStr}`);
  }

  query(req?: Pagination): Observable<HttpResponse<IOrganization[]>> {
    const options = createRequestOption(req);
    return this.http.get<IOrganization[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(login: string): Observable<{}> {
    return this.http.delete(`${this.resourceUrl}/${login}`);
  }

  authorities(): Observable<string[]> {
    return this.http.get<string[]>(this.applicationConfigService.getEndpointFor('api/authorities'));
  }
}
