import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DEBUG_INFO_ENABLED } from 'app/app.constants';
import { AccountModule } from './account/account.module';
import { AdminRoutingModule } from './admin/admin-routing.module';
import { MAIN_ROUTER } from './app-rounting.routing';
import { Authority } from './config/authority.constants';
import { UserRouteAccessService } from './core/auth/user-route-access.service';
import { EntityRoutingModule } from './entities/entity-routing.module';
import { errorRoute } from './layouts/error/error.route';
import { navbarRoute } from './layouts/navbar/navbar.route';
import { LoginModule } from './login/login.module';

/*
@NgModule({
  imports: [
    RouterModule.forRoot(
      [
        {
          path: 'admin',
          data: {
            authorities: [Authority.ADMIN],
          },
          canActivate: [UserRouteAccessService],
          //loadChildren: () => import('./admin/admin-routing.module').then(m => m.AdminRoutingModule),
          loadChildren: () => AdminRoutingModule
        },
        {
          path: 'account',
          //loadChildren: () => import('./account/account.module').then(m => m.AccountModule),
          loadChildren: () => AccountModule
        },
        {
          path: 'login',
          //loadChildren: () => import('./login/login.module').then(m => m.LoginModule),
          loadChildren: () => LoginModule
        },
        {
          path: '',
          //loadChildren: () => import(`./entities/entity-routing.module`).then(m => m.EntityRoutingModule),
          loadChildren: () => EntityRoutingModule
        },
        navbarRoute,
        ...errorRoute,
      ],
      { enableTracing: DEBUG_INFO_ENABLED }
    ),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {}
*/

@NgModule({
  imports: [
    RouterModule.forRoot(MAIN_ROUTER, { enableTracing: DEBUG_INFO_ENABLED }),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {}
