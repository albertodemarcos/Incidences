import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StatusIncidenceComponent } from './status-incidence.component';

describe('StatusIncidenceComponent', () => {
  let component: StatusIncidenceComponent;
  let fixture: ComponentFixture<StatusIncidenceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StatusIncidenceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StatusIncidenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
