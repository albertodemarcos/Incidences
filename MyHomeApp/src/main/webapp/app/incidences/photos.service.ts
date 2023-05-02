import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PhotosService {


  private resourceUrl = this.applicationConfigService.getEndpointFor('/api');

  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}


  find(bucketName: string, fileName: any): Observable<any> {
    return this.http.get<any>(`${this.resourceUrl}/downloadS3File?bucketName=${bucketName}&fileName=${fileName}`);
  }
}
