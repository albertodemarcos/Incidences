import { HttpResponse, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ASC, DESC, SORT } from 'app/config/navigation.constants';
import { ITEMS_PER_PAGE } from 'app/config/pagination.constants';
import { combineLatest } from 'rxjs';
import { DeleteIncidenceComponent } from '../delete/delete-incidence.component';
import { Incidence } from '../../core/model/incidence.model';
import { IncidencesService } from '../incidences.service';
import { IncidenceListDTO } from '../../core/model/incidenceListDTO.model';

@Component({
  selector: 'jhi-list-incidence',
  templateUrl: './list-incidence.component.html'
})
export class ListIncidenceComponent implements OnInit {

  incidences: IncidenceListDTO[] | null = null;
  isLoading = false;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  incidencesStatus:Array<string> = ['PENDING','IN_PROCESS','RESOLVED','CANCELED'];

  //filters
  title: string = '';
  startDate: Date | undefined;
  endDate: Date | undefined;
  status: string = '';
  nameOrganization: string = '';

  activated: Boolean | undefined;
  lastModifiedBy: string = '';
  createdDate: Date | undefined;
  lastModifiedDate: Date | undefined;

 constructor(
    private incidencesService: IncidencesService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {    
    this.handleNavigation();
  }

  /*setActive(incidence: Incidence, isActivated: boolean): void {
    this.incidencesService.update({ ...incidence, activated: isActivated }).subscribe(() => this.loadAll());
  }*/

  trackIdentity(_index: number, item: Incidence): number {
    return item.id!;
  }

  deleteIncidence(incidence: Incidence): void {
    const modalRef = this.modalService.open(DeleteIncidenceComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.incidence = incidence;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }

  loadFilter(): void{
    this.loadAll();
  }  

  loadAll(): void {
    this.isLoading = true;
    let filters =  this.filter();
    this.incidencesService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
        filter: filters
      })
      .subscribe({
        next: (res: HttpResponse<IncidenceListDTO[]>) => {
          this.isLoading = false;
          this.onSuccess(res.body, res.headers);
        },
        error: () => (this.isLoading = false),
      });
  }

  transition(): void {
    this.router.navigate(['./'], {
      relativeTo: this.activatedRoute.parent,
      queryParams: {
        page: this.page,
        sort: `${this.predicate},${this.ascending ? ASC : DESC}`,
      },
    });
  }

  private handleNavigation(): void {
    combineLatest([this.activatedRoute.data, this.activatedRoute.queryParamMap]).subscribe(([data, params]) => {
      const page = params.get('page');
      this.page = +(page ?? 1);
      const sort = (params.get(SORT) ?? data['defaultSort']).split(',');
      this.predicate = sort[0];
      this.ascending = sort[1] === ASC;
      this.loadAll();
    });
  }

  private sort(): string[] {
    const result = [`${this.predicate},${this.ascending ? ASC : DESC}`];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  private filter(): Map<string, string> {

    let filters: Map<string, string> = new Map<string, string>();

    if( this.title != null && this.title.trim() !== '' ){

      filters.set('title', this.title);

    }else if( this.status != null && this.status.trim() !== '' ){

      filters.set('status', this.status);

    }else if( this.startDate != null ){

      filters.set('startDate', this.getDateFormat(this.startDate) );

    }else if( this.endDate != null ){

      filters.set('endDate', this.getDateFormat(this.endDate) );
   
    }else if( this.nameOrganization != null ){

      filters.set('nameOrganization', this.nameOrganization );
    }

    return filters;
  }

  private onSuccess(incidences: IncidenceListDTO[] | null, headers: HttpHeaders): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.incidences = incidences;
  }

  /**
   * Method yyyy-MM-dd HH:mm
   * @param date 
   * @returns 
   */
  private getDateFormat(date: any): string{

    if( date == null ){
      return '';
    }

    return date.replace('T', ' ');
    /*let day = (date.getDate() < 10 ? '0' : '') +date.getDate();
    let month = (date.getMonth() < 10 ? '0' : '')+(date.getMonth() + 1);
    let year = date.getFullYear();

    let hour = date.getHours();
    let min = date.getMinutes();

    return `${year}-${month}-${day} ${hour}:${min}`;*/
  }

}
