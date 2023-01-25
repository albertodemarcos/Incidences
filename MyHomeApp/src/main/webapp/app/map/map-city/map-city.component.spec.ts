import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MapCityComponent } from './map-city.component';

describe('MapCityComponent', () => {
  let component: MapCityComponent;
  let fixture: ComponentFixture<MapCityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MapCityComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MapCityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
