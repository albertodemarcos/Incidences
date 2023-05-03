import { Component, OnInit } from '@angular/core';
import { IncidenceKanbanDTO } from 'app/core/model/incidenceKanbanDTO.model';
import { IncidencesService } from '../incidences.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'jhi-update-employee-incidence',
  templateUrl: './update-employee-incidence.component.html'
})
export class UpdateEmployeeIncidenceComponent implements OnInit {

  incidence?: IncidenceKanbanDTO;

  constructor(private incidencesService: IncidencesService, private activeModal: NgbActiveModal) {}
  
  ngOnInit(): void {
  }

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmUpdateEmployeeIncidence(id: number): void {
    this.incidencesService.delete(id.toString()).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }

}
