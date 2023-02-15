import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteIncidenceComponent } from './delete-incidence.component';

describe('DeleteIncidenceComponent', () => {
  let component: DeleteIncidenceComponent;
  let fixture: ComponentFixture<DeleteIncidenceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeleteIncidenceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeleteIncidenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
