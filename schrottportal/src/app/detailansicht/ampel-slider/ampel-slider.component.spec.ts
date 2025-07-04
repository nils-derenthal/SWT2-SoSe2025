import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AmpelSliderComponent } from './ampel-slider.component';

describe('AmpelSliderComponent', () => {
  let component: AmpelSliderComponent;
  let fixture: ComponentFixture<AmpelSliderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AmpelSliderComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(AmpelSliderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
