import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailansichtComponent } from './detailansicht.component';

describe('DetailansichtComponent', () => {
  let component: DetailansichtComponent;
  let fixture: ComponentFixture<DetailansichtComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailansichtComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(DetailansichtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
