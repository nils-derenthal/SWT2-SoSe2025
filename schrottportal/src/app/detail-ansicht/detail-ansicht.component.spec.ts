import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailAnsichtComponent } from './detail-ansicht.component';

describe('DetailAnsichtComponent', () => {
  let component: DetailAnsichtComponent;
  let fixture: ComponentFixture<DetailAnsichtComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailAnsichtComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailAnsichtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
