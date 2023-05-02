import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Incidence } from '../../core/model/incidence.model';
import { IncidencesService } from '../incidences.service';

@Component({
  selector: 'jhi-delete-incidence',
  templateUrl: './delete-incidence.component.html'
})
export class DeleteIncidenceComponent implements OnInit {

  incidence?: Incidence;

  constructor(private incidencesService: IncidencesService, private activeModal: NgbActiveModal) {}
  
  ngOnInit(): void {
  }

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.incidencesService.delete(id.toString()).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
