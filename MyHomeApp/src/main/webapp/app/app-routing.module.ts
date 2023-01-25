import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DEBUG_INFO_ENABLED } from 'app/app.constants';
import { MAIN_ROUTER } from './app-rounting.routing';

@NgModule({
  imports: [
    RouterModule.forRoot(MAIN_ROUTER, { enableTracing: DEBUG_INFO_ENABLED }),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {}
