import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KalenderComponent } from './kalender.component';

describe('KalenderComponent', () => {
  let component: KalenderComponent;
  let fixture: ComponentFixture<KalenderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [KalenderComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KalenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
