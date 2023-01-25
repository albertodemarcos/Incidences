import { Routes } from '@angular/router';


//Components
import { CreateOrganizationComponent } from './create/create-organization.component';
import { DetailOrganizationComponent } from './detail/detail-organization.component';
import { ListOrganizationsComponent } from './list/list-organizations.component';

/*@Injectable({ providedIn: 'root' })
export class UserManagementResolve implements Resolve<IUser | null> {
  constructor(private service: UserManagementService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUser | null> {
    const id = route.params['login'];
    if (id) {
      return this.service.find(id);
    }
    return of(null);
  }
}*/

export const organizationsRoute: Routes = [
  {
    path: '',
    component: ListOrganizationsComponent,
    data: {
      defaultSort: 'id,asc',
    },
  },
  {
    path: ':login/view',
    component: DetailOrganizationComponent,
  },
  {
    path: 'new',
    component: CreateOrganizationComponent,  
  },
  {
    path: ':login/edit',
    component: CreateOrganizationComponent,
  },
];