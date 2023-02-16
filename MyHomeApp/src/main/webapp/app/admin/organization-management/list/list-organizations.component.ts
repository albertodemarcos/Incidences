import { HttpResponse, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ASC, DESC, SORT } from 'app/config/navigation.constants';
import { ITEMS_PER_PAGE } from 'app/config/pagination.constants';
import { combineLatest } from 'rxjs';
import { DeleteOrganizationComponent } from '../delete/delete-organization.component';
import { Organization } from '../organization.model';
import { OrganizationService } from '../organization.service';

@Component({
  selector: 'jhi-list-organizations',
  templateUrl: './list-organizations.component.html'
})
export class ListOrganizationsComponent implements OnInit {

  organizations: Organization[] | null = null;
  isLoading = false;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  organizationsTypes:Array<string> = ['TOWN_HALL','COMMONWEALTH','ADMINISTRATIVE_REGIONS','STATE','CONFEDERATION','PRINCIPAL'];
  activeStatus:Array<any> = [null, true, false];

  //filters
  name: string = '';
  type: string = '';
  activated: Boolean | undefined;
  lastModifiedBy: string = '';
  createdDate: Date | undefined;
  lastModifiedDate: Date | undefined;

 constructor(
    private organizationService: OrganizationService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {    
    this.handleNavigation();
  }

  setActive(organization: Organization, isActivated: boolean): void {
    this.organizationService.update({ ...organization, activated: isActivated }).subscribe(() => this.loadAll());
  }

  trackIdentity(_index: number, item: Organization): number {
    return item.id!;
  }

  deleteOrganization(organization: Organization): void {
    const modalRef = this.modalService.open(DeleteOrganizationComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.organization = organization;
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
    this.organizationService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
        filter: filters
      })
      .subscribe({
        next: (res: HttpResponse<Organization[]>) => {
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

    if( this.name != null && this.name.trim() !== '' ){

      filters.set('name', this.name);

    }else if( this.type != null && this.type.trim() !== '' ){

      filters.set('type', this.type);

    }else if( this.activated != null ){

      let activated = this.activated == null ? 'null' : (this.activated != true ? 'false' : 'true');

      filters.set('activated', activated);

    }else if( this.createdDate != null ){

      //filters.push(this.createdDate);

    }else if( this.lastModifiedDate != null ){

      //filters.push(this.name);

    }else if( this.lastModifiedBy != null  && this.lastModifiedBy.trim() !== '' ){

      filters.set('lastModifiedBy', this.lastModifiedBy);
    }

    return filters;
  }

  private onSuccess(organizations: Organization[] | null, headers: HttpHeaders): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.organizations = organizations;
  }

}
