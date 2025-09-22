import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AutorListItemComponent } from './autor-list-item.component';

describe('AutorListItemComponent', () => {
  let component: AutorListItemComponent;
  let fixture: ComponentFixture<AutorListItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
    imports: [AutorListItemComponent]
})
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AutorListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
