import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UebersichtComponent } from './uebersicht.component';

describe('UebersichtComponent', () => {
  let component: UebersichtComponent;
  let fixture: ComponentFixture<UebersichtComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UebersichtComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UebersichtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
