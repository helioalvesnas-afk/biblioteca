import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GeneroListItemComponent } from './genero-list-item.component';

describe('GeneroListItemComponent', () => {
  let component: GeneroListItemComponent;
  let fixture: ComponentFixture<GeneroListItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
    imports: [GeneroListItemComponent]
})
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GeneroListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
