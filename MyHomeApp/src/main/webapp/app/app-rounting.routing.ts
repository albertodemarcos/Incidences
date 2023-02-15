import { Routes } from '@angular/router';

//ROUTING
import { navbarRoute } from './layouts/navbar/navbar.route';
import { errorRoute } from './layouts/error/error.route';
//MODULES
import { AccountModule } from './account/account.module';
import { AdminRoutingModule } from './admin/admin-routing.module';
import { EntityRoutingModule } from './entities/entity-routing.module';
import { LoginModule } from './login/login.module';
import { OrganizationModule } from './admin/organization-management/organization.module';
import { MapCityModule } from './map-city/map-city.module';
import { IncidencesModule } from './incidences/incidences.module';

//SERVICES
import { UserRouteAccessService } from './core/auth/user-route-access.service';
//OTHER
import { Authority } from './config/authority.constants';

export const MAIN_ROUTER: Routes = [
  {
    path: 'admin',
    data: {
      authorities: [Authority.ADMIN],
    },
    canActivate: [UserRouteAccessService],
    loadChildren: () => AdminRoutingModule
  },
  {
    path: 'account',
    loadChildren: () => AccountModule
  },
  {
    path: 'login',
    loadChildren: () => LoginModule
  },
  {
    path: 'organizations',
    loadChildren: () => OrganizationModule
  },
  {
    path: 'incidences',
    loadChildren: () => IncidencesModule
  },
  {
    path: 'map',
    loadChildren: () => MapCityModule
  },
  {
    path: '',
    loadChildren: () => EntityRoutingModule
  },
  navbarRoute,
  ...errorRoute,
];

/*
  [
    {
      path: 'admin',
      data: {
        authorities: [Authority.ADMIN],
      },
      canActivate: [UserRouteAccessService],
      loadChildren: () => import('./admin/admin-routing.module').then(m => m.AdminRoutingModule),
    },
    {
      path: 'account',
      loadChildren: () => import('./account/account.module').then(m => m.AccountModule),
    },
    {
      path: 'login',
      loadChildren: () => import('./login/login.module').then(m => m.LoginModule),
    },
    {
      path: '',
      loadChildren: () => import(`./entities/entity-routing.module`).then(m => m.EntityRoutingModule),
    },
    navbarRoute,
    ...errorRoute,
  ],
  { enableTracing: DEBUG_INFO_ENABLED }
),
]
*/