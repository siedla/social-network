import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayFriendsComponent } from './display-friends.component';

describe('DisplayFriendsComponent', () => {
  let component: DisplayFriendsComponent;
  let fixture: ComponentFixture<DisplayFriendsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayFriendsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayFriendsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
