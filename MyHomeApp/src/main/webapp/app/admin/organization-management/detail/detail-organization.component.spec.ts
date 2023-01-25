import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailOrganizationComponent } from './detail-organization.component';

describe('DetailOrganizationComponent', () => {
  let component: DetailOrganizationComponent;
  let fixture: ComponentFixture<DetailOrganizationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailOrganizationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailOrganizationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
