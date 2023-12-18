import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FishCardComponent } from './fish-card.component';

describe('FishCardComponent', () => {
  let component: FishCardComponent;
  let fixture: ComponentFixture<FishCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FishCardComponent]
    });
    fixture = TestBed.createComponent(FishCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
