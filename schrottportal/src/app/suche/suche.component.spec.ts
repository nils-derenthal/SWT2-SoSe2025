import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SucheComponent } from './suche.component';

describe('SucheComponent', () => {
  let component: SucheComponent;
  let fixture: ComponentFixture<SucheComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SucheComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(SucheComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
