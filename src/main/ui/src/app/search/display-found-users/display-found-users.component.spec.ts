import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayFoundUsersComponent } from './display-found-users.component';

describe('DisplayFoundUsersComponent', () => {
  let component: DisplayFoundUsersComponent;
  let fixture: ComponentFixture<DisplayFoundUsersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayFoundUsersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayFoundUsersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
