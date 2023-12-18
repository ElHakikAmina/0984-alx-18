import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HuntsComponent } from './hunts.component';

describe('HuntsComponent', () => {
  let component: HuntsComponent;
  let fixture: ComponentFixture<HuntsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HuntsComponent]
    });
    fixture = TestBed.createComponent(HuntsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
