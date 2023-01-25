import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListIncidenceComponent } from './list-incidence.component';

describe('ListIncidenceComponent', () => {
  let component: ListIncidenceComponent;
  let fixture: ComponentFixture<ListIncidenceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListIncidenceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListIncidenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
