import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SingleImmoMapComponent } from './single-immo-map.component';

describe('SingleImmoMapComponent', () => {
  let component: SingleImmoMapComponent;
  let fixture: ComponentFixture<SingleImmoMapComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SingleImmoMapComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SingleImmoMapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
