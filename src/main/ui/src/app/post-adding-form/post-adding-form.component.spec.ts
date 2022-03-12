import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostAddingFormComponent } from './post-adding-form.component';

describe('PostAddingFormComponent', () => {
  let component: PostAddingFormComponent;
  let fixture: ComponentFixture<PostAddingFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PostAddingFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PostAddingFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
