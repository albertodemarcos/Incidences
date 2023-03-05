import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailIncidenceModalComponent } from './detail-incidence-modal.component';

describe('DetailIncidenceModalComponent', () => {
  let component: DetailIncidenceModalComponent;
  let fixture: ComponentFixture<DetailIncidenceModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailIncidenceModalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailIncidenceModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
