import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateIncidenceComponent } from './create-incidence.component';

describe('CreateIncidenceComponent', () => {
  let component: CreateIncidenceComponent;
  let fixture: ComponentFixture<CreateIncidenceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateIncidenceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateIncidenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
