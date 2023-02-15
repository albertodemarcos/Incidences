import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailIncidenceComponent } from './detail-incidence.component';

describe('DetailIncidenceComponent', () => {
  let component: DetailIncidenceComponent;
  let fixture: ComponentFixture<DetailIncidenceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailIncidenceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailIncidenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
