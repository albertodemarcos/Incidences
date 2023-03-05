import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateIncidenceModalComponent } from './update-incidence-modal.component';

describe('UpdateIncidenceModalComponent', () => {
  let component: UpdateIncidenceModalComponent;
  let fixture: ComponentFixture<UpdateIncidenceModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateIncidenceModalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateIncidenceModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
