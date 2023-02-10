import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Organization } from '../organization.model';
import { OrganizationService } from '../organization.service';

@Component({
  selector: 'jhi-delete-organization',
  templateUrl: './delete-organization.component.html'
})
export class DeleteOrganizationComponent implements OnInit {

  organization?: Organization;

  constructor(private organizationService: OrganizationService, private activeModal: NgbActiveModal) {}
  
  ngOnInit(): void {
  }

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.organizationService.delete(id.toString()).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }

}
