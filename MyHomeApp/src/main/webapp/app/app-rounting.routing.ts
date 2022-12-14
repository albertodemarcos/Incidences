/*const routes: Routes = [
    {path : '', component: MainComponent, 
    children:[
        { path: '', canActivate: [UserRouteAccessService], loadChildren: () => EntityRoutingModule, },
        { path: 'admin', data: {authorities: [Authority.ADMIN],}, canActivate: [UserRouteAccessService], loadChildren: () => AdminRoutingModule, },
        { path: 'ayuda', loadChildren: () => ConstruccionModule, },
        { path: 'account', loadChildren: () => AccountModule, },
        { path: 'login',   loadChildren: () => LoginModule, },
        { path: 'index', canActivate: [UserRouteAccessService], loadChildren: () => IndexModule, },
        { path : 'modal', component : ModalComponent, outlet : 'modal', canActivate: [UserRouteAccessService],  
        children: [
            { path : 'incidences', loadChildren: () => IncidenceModule },
        ]},
    ]},
    navbarRoute,
    ...errorRoute,
  ];*/

import { Routes } from '@angular/router';

//ROUTING
import { navbarRoute } from './layouts/navbar/navbar.route';
import { errorRoute } from './layouts/error/error.route';
//MODULES
import { AccountModule } from './account/account.module';
import { AdminRoutingModule } from './admin/admin-routing.module';
import { EntityRoutingModule } from './entities/entity-routing.module';
import { LoginModule } from './login/login.module';
// COMPONENTS
import { MainComponent } from './layouts/main/main.component';
//SERVICES
import { UserRouteAccessService } from './core/auth/user-route-access.service';
//OTHER
import { Authority } from './config/authority.constants';

export const MAIN_ROUTER: Routes = [
  {path : '', component: MainComponent, 
  children:[
      { path: '', canActivate: [UserRouteAccessService], loadChildren: () => EntityRoutingModule, },
      { path: 'admin', data: {authorities: [Authority.ADMIN],}, canActivate: [UserRouteAccessService], loadChildren: () => AdminRoutingModule, },
      { path: 'account', loadChildren: () => AccountModule, },
      { path: 'login',   loadChildren: () => LoginModule, },
      //{ path: 'index', canActivate: [UserRouteAccessService], loadChildren: () => IndexModule, },
      /*{ path : 'modal', component : ModalComponent, outlet : 'modal', canActivate: [UserRouteAccessService],  
      children: [
          { path : 'incidences', loadChildren: () => IncidenceModule },
      ]},*/
  ]},
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