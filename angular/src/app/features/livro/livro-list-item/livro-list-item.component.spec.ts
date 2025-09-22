import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LivroListItemComponent } from './livro-list-item.component';

describe('GeneroListItemComponent', () => {
  let component: LivroListItemComponent;
  let fixture: ComponentFixture<LivroListItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
    imports: [LivroListItemComponent]
})
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LivroListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
