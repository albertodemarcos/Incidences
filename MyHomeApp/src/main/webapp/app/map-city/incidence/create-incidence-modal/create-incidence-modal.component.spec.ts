import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateIncidenceModalComponent } from './create-incidence-modal.component';

describe('CreateIncidenceModalComponent', () => {
  let component: CreateIncidenceModalComponent;
  let fixture: ComponentFixture<CreateIncidenceModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateIncidenceModalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateIncidenceModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
