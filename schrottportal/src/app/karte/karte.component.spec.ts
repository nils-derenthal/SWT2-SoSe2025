import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KarteComponent } from './karte.component';

describe('KarteComponent', () => {
  let component: KarteComponent;
  let fixture: ComponentFixture<KarteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [KarteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KarteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
