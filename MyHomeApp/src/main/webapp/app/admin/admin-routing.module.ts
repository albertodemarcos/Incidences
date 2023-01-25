import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ConfigurationModule } from './configuration/configuration.module';
import { DocsModule } from './docs/docs.module';
import { HealthModule } from './health/health.module';
import { LogsModule } from './logs/logs.module';
import { MetricsModule } from './metrics/metrics.module';
import { OrganizationModule } from './organization-management/organization.module';
import { UserManagementModule } from './user-management/user-management.module';
/* jhipster-needle-add-admin-module-import - JHipster will add admin modules imports here */

@NgModule({
  imports: [
    /* jhipster-needle-add-admin-module - JHipster will add admin modules here */
    RouterModule.forChild([
      {
        path: 'user-management',
        //loadChildren: () => import('./user-management/user-management.module').then(m => m.UserManagementModule),
        loadChildren: () => UserManagementModule,
        data: {
          pageTitle: 'userManagement.home.title',
        },
      },
      {
        path: 'docs',
        //loadChildren: () => import('./docs/docs.module').then(m => m.DocsModule),
        loadChildren: () => DocsModule,
      },
      {
        path: 'configuration',
        //loadChildren: () => import('./configuration/configuration.module').then(m => m.ConfigurationModule),
        loadChildren: () => ConfigurationModule,
      },
      {
        path: 'health',
        //loadChildren: () => import('./health/health.module').then(m => m.HealthModule),
        loadChildren: () => HealthModule,
      },
      {
        path: 'logs',
        //loadChildren: () => import('./logs/logs.module').then(m => m.LogsModule),
        loadChildren: () => LogsModule,
      },
      {
        path: 'metrics',
        //loadChildren: () => import('./metrics/metrics.module').then(m => m.MetricsModule),
        loadChildren: () => MetricsModule,
      },
      {
        path: 'organizations',
        loadChildren: ()=> OrganizationModule,
      }
      /* jhipster-needle-add-admin-route - JHipster will add admin routes here */
    ]),
  ],
})
export class AdminRoutingModule {}
