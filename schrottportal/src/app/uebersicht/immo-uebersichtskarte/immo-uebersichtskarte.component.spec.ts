import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImmoUebersichtskarteComponent } from './immo-uebersichtskarte.component';

describe('ImmoUebersichtskarteComponent', () => {
  let component: ImmoUebersichtskarteComponent;
  let fixture: ComponentFixture<ImmoUebersichtskarteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ImmoUebersichtskarteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ImmoUebersichtskarteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
