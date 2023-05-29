import { Component, OnInit } from '@angular/core';
import { OrganizationService } from '../organization.service';

@Component({
  selector: 'jhi-importer',
  templateUrl: './importer.component.html',
  styleUrls: ['./importer.component.scss']
})
export class ImporterComponent implements OnInit {

  isSaving: boolean = false;


  constructor(private organizationService: OrganizationService) { }

  ngOnInit(): void {
  }

  save(): void{

    this.organizationService.importer().subscribe({
      next: (result: string) => {
        console.info('result: ' + result);
      },
      error: (err) => {
        console.error('result: ' + err);
      }
    });

  }


}
